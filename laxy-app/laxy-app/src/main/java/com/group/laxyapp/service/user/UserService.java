package com.group.laxyapp.service.user;

import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.user.request.UpdateNickNameReq;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import com.group.laxyapp.service.token.ResponseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void registUser(UserRegistRequest request) {
        User u = new User(request.getNickname(), request.getAge(), request.getGender());
        userRepository.save(u);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        List<User> users = userRepository.findAll();
        if (users == null) {
            throw new IllegalArgumentException();
        }
        return users.stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) throws IllegalArgumentException {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateNickname(request.getNickname());
    }

    @Transactional
    public void deleteUser(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }

    @Transactional
    public void modifyNickName(Long nickname, UpdateNickNameReq updateNickNameReq) throws ResponseException {
        User user = userRepository.findById(nickname)
                .orElseThrow(() -> new ResponseException("User not found"));
        user.updateNickname(updateNickNameReq.getNewNickName());
        userRepository.save(user);
    }
}
