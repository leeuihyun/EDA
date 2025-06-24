package com.example.demo.domain.users.dto.response;

import com.example.demo.domain.users.entity.Users;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignResponse {

    private Long id;
    private String email;
    private int point;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static SignResponse of(Users user) {
        return new SignResponse(
            user.getId(),
            user.getEmail(),
            user.getPoint(),
            user.getCreatedAt(),
            user.getUpdatedAt()
        );
    }
}
