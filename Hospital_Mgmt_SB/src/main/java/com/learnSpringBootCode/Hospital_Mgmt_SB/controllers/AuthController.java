package com.learnSpringBootCode.Hospital_Mgmt_SB.controllers;

import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.LoginRequestDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.LoginResponseDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.dto.SignupResponseDto;
import com.learnSpringBootCode.Hospital_Mgmt_SB.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDto> signup(@RequestBody LoginRequestDto signupRequestDto){
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
