package com.learnSpringBootCode.Hospital_Mgmt_SB.security;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.LoginRequestDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.LoginResponseDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.SignupResponseDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.User;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.type.AuthProviderTypeEnum;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );
        User user  = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto().builder().jwt(token).userId(user.getId()).build();
    }

    //This is controller SignUp Method
    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        /// moved the logic of finding User based on signup details to aa function
        User user = getUserFromSignUpRequestDto(signupRequestDto, AuthProviderTypeEnum.EMAIL, null);

        return modelMapper.map(user,SignupResponseDto.class);
    }

    @Transactional
    public ResponseEntity<LoginResponseDto> handleOAuth2LoginRequest(OAuth2User oAuth2User, String registrationId) {

        /// here we have to get provider type and provider ID, eg: provider type : Google, some unique id for google provider
        /// after that save the provider type and id with user info.
        /// and if user account exists in the system, then directly login that user
        /// otherwise, redirect to signup page, to signup the user first
        AuthProviderTypeEnum authProviderTypeEnum = authUtil.getProviderTypeFromRegistrationForm(registrationId);
        String providerId = authUtil.determineProviderIdFromOAuth2User(oAuth2User,registrationId);

        User user = userRepository.findByProviderIdAndProviderType(providerId,authProviderTypeEnum).orElse(null);

        /// in some scenarios it can happen that, firstly User had login to system via email and password, and then afterwards it trys to login through
        /// google or github etc, and from there also we get the same email, then it depends on the Business requried
        /// -> do we have allow that flow, and further merge that user
        /// -> don't allow that flow
        String email = oAuth2User.getAttribute("email");

        User emailUser = userRepository.findByUsername(email).orElse(null);

        /// this is the case when user haven't signed up yet from google or github or like that, so will signup that user
        if(user == null && emailUser == null){
            String username = authUtil.determineUsernameFromOAuth2User(oAuth2User, registrationId, providerId);
            ///here password will remain null, because user is getting signed up via Google or github like entities,
            ///so will only save username for that users.
            user = getUserFromSignUpRequestDto(new LoginRequestDto(username,null), authProviderTypeEnum, providerId);
        }

        /// if for eg; user have signedup from github account, and haven't given us the email accesss, so our user is created based on username of github,
        /// now in future, it adds the email access in github account, and now we have the user, but its email was blank, and it trys to signup again,
        /// then have to save that email against that user in our DB.
        else if (user!=null) {
            if(email !=null && !email.isBlank() && !email.equals(user.getUsername())){
                user.setUsername(email);
                userRepository.save(user);
            }
        }

        /// Now this case works, when user have previous signed up in the system via email and password, and now its tryting to signpu using Google or Github,
        /// then we have to restrict user to signup via that flow, and show the error message for that.
        /// condition for this case : user is null && emailUser is not null.
        else{
            throw new BadCredentialsException("This email is already registered with provider :"+ emailUser.getProviderId());
        }

        LoginResponseDto loginResponseDto = new LoginResponseDto().builder()
                .jwt(authUtil.generateAccessToken(user))
                .userId(user.getId())
                .build();

        return ResponseEntity.ok(loginResponseDto);
    }

    // Internal Methods
    public User getUserFromSignUpRequestDto(LoginRequestDto signupRequestDto, AuthProviderTypeEnum authProviderType, String providerId){
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if (user != null){
            throw  new IllegalArgumentException("User already exists with this username");
        }

        user = User.builder()
                .username(signupRequestDto.getUsername())
                .providerId(providerId)
                .providerType(authProviderType)
                .build();

        if (authProviderType == AuthProviderTypeEnum.EMAIL){
            user.setPassword(passwordEncoder.encode(signupRequestDto.getPassword()));
        }
        return userRepository.save(user);
    }
}
