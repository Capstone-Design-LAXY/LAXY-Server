package com.group.laxyapp.controller.user;

import com.group.laxyapp.dto.user.request.UpdateNickNameReq;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
import com.group.laxyapp.dto.user.response.ResponseTemplate;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.service.token.JwtService;
import com.group.laxyapp.service.token.ResponseException;
import com.group.laxyapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/user")
    public void registUser(@RequestBody UserRegistRequest regist_request) {
        userService.registUser(regist_request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/mypage")
    public void updateUser(@RequestBody UserUpdateRequest update_request) {
        userService.updateUser(update_request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam("nickname") String nickname) {
        userService.deleteUser(nickname);
    }

    /**
     * 유저정보변경 API
     * [PATCH] /members/:memberId
     */
    @PatchMapping("/mypage/{nickname}")
    public ResponseTemplate<String> modifyUserName(@PathVariable("nickname") Long memberId, @RequestBody UpdateNickNameReq updateNickNameReq) {
        try {
            // JWT에서 userIdx 추출
            Long userIdxByJwt = jwtService.getmemberId();
            // userIdx와 접근한 유저가 같은지 확인
            if (!memberId.equals(userIdxByJwt)) {
                return new ResponseTemplate<>("No JWT");
            }
            // 닉네임 수정
            userService.modifyNickName(memberId, updateNickNameReq);

            String result = "회원정보가 수정되었습니다.";
            return new ResponseTemplate<>(result);
        } catch (ResponseException exception) {
            return new ResponseTemplate<>(exception.getMessage());
        }
    }
}
