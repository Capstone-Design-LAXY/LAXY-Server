//package com.group.laxyapp.dto.user.request;

//public class UserUpdateRequest {

//    private long id;
//    private String nickname;

//    public long getId() {
//        return id;
//    }

//    public String getNickname() {
//        return nickname;
//    }
//}

package com.group.laxyapp.dto.user.request;

public class UserUpdateRequest {
    private long id;
    private String nickname;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
