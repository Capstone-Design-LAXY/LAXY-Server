package com.group.laxyapp.service.user;

import com.group.laxyapp.dto.user.request.UserDeleteRequest;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
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

    public void registUser(UserRegistRequest regist_request) {
        userRepository.registUser(regist_request.getNickname());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest update_request) {
        if (userRepository.userNotExist(update_request.getId())) {
            throw new IllegalArgumentException("존재하지않는 사용자입니다.");
        }

        userRepository.updateUser(update_request.getNickname(), update_request.getId());
    }

    public void deleteUser(UserDeleteRequest delete_request) {
        if(userRepository.userNotExist(delete_request.getNickname())) {
            throw new IllegalArgumentException("존재하지않는 사용자입니다.");
        }

        userRepository.deleteUser(delete_request.getNickname());
    }
}
