package com.group.laxyapp.dto.user.request;

import lombok.Data;

@Data
public class UserLoginRequest {

    private String loginId;
    private String password;
}