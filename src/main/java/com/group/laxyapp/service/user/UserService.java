package com.group.laxyapp.service.user;

import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void registUser(UserRegistRequest request) {

        userRepository.registUser(request.getNickname());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }
}
