package com.group.laxyapp.controller.user;


import com.group.laxyapp.dto.user.request.UserDeleteRequest;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
    public void deleteUser(@RequestBody UserDeleteRequest delete_request) {
        userService.deleteUser(delete_request);
    }

}
