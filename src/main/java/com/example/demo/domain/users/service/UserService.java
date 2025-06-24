package com.example.demo.domain.users.service;

import com.example.demo.domain.users.dto.request.SignRequest;
import com.example.demo.domain.users.dto.response.SignResponse;
import com.example.demo.domain.users.entity.Users;
import com.example.demo.domain.users.repository.UserRepository;
import com.example.demo.global.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SignResponse signUp(SignRequest signRequest) {

        if (userRepository.findByEmail(signRequest.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "duplicated email");
        }

        Users user = Users.builder()
            .email(signRequest.getEmail())
            .password(passwordEncoder.encode(signRequest.getPassword()))
            .build();

        Users newUser = userRepository.save(user);

        return SignResponse.of(newUser);
    }

    public SignResponse signIn(SignRequest signRequest) {
        Users user = userRepository.findByEmail(signRequest.getEmail())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));

        if (!passwordEncoder.matches(signRequest.getPassword(), user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "incorrect password");
        }

        return SignResponse.of(user);
    }
}
