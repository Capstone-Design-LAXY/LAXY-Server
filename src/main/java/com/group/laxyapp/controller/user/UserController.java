package com.group.laxyapp.controller.user;


import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.repository.user.UserRepository;
import com.group.laxyapp.service.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public void registUser(@RequestBody UserRegistRequest request) {
        userService.registUser(request);
    }
}
