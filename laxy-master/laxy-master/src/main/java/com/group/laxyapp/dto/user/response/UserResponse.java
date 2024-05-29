package com.group.laxyapp.dto.user.response;

public class UserResponse {
    private long id;
    private String nickname;

    public UserResponse(long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
