package com.group.laxyapp.dto.user.request;
import com.group.laxyapp.controller.user.UserRole;
import com.group.laxyapp.domain.user.User;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserRegistRequest {
    private String loginId;
    private String password;
    private String passwordCheck;
    private String nickname;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .nickname(nickname)
                .userRole(UserRole.L1)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
