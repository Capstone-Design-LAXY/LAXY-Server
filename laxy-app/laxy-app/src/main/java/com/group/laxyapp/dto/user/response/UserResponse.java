package com.group.laxyapp.dto.user.response;

import com.group.laxyapp.domain.user.User;

public class UserResponse {
    private long id;
    private String nickname;
    private Integer age;
    private String gender;

    public UserResponse(long id, String nickname, Integer age, String gender) {
        this.id = id;
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.age = user.getAge();
        this.gender = user.getGender();
    }

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
