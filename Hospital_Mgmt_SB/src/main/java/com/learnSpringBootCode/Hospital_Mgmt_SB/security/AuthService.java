package com.learnSpringBootCode.Hospital_Mgmt_SB.security;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.LoginRequestDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.LoginResponseDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.SignupResponseDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.entities.User;
import com.learnSpringBootCode.Hospital_Mgmt_SB.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    public SignupResponseDto signup(LoginRequestDto signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUsername()).orElse(null);

        if (user != null){
            throw  new IllegalArgumentException("User already exists with this username");
        }

        user = userRepository.save(User.builder()
                .username(signupRequestDto.getUsername())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build()
        );

        return modelMapper.map(user,SignupResponseDto.class);
    }
}
