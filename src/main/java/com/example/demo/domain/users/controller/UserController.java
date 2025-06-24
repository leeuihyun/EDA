package com.example.demo.domain.users.controller;

import com.example.demo.domain.users.dto.request.SignRequest;
import com.example.demo.domain.users.dto.response.SignResponse;
import com.example.demo.domain.users.service.UserService;
import com.example.demo.global.dto.ApiResponse;
import com.example.demo.global.jwt.JwtProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignResponse>> signUp(
        @RequestBody @Valid SignRequest signRequest) {

        SignResponse response = userService.signUp(signRequest);

        return ResponseEntity.ok(ApiResponse.success("회원가입 성공", response));
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponse<SignResponse>> signIn(
        @RequestBody @Valid SignRequest signRequest) {

        SignResponse response = userService.signIn(signRequest);

        String token = jwtProvider.generateToken(response.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + token);

        return ResponseEntity
            .ok()
            .headers(headers)
            .body(ApiResponse.success("로그인 성공", response));
    }
}
