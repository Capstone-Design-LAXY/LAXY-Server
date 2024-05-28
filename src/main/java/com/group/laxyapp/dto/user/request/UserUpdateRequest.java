package com.group.laxyapp.dto.user.request;

public class UserUpdateRequest {

    private long id;
    private String nickname;
    private Integer age;
    private String gender;

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public Integer getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }
}

