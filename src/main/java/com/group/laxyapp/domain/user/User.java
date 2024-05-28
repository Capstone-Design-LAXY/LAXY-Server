package com.group.laxyapp.domain.user;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @Column(nullable = false, length = 20)
    private String nickname;
    private Integer age;
    private String gender;

    public User() {}

    public User(String nickname, Integer age, String gender) {
        if(nickname == null || nickname.isBlank()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", nickname));
        }

        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
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

    public Long getId() {
        return id;
    }

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }

    public void updateUserInfo(String nickname, Integer age, String gender) {
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }
}
