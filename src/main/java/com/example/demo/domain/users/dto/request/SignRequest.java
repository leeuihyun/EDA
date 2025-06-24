package com.example.demo.domain.users.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignRequest {

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;
}
