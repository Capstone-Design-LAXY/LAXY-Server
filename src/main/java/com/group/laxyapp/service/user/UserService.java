package com.group.laxyapp.service.user;

import com.group.laxyapp.domain.user.User;
import com.group.laxyapp.domain.user.UserRepository;
import com.group.laxyapp.dto.user.request.UserRegistRequest;
import com.group.laxyapp.dto.user.request.UserUpdateRequest;
import com.group.laxyapp.dto.user.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository;

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

//        return userRepository.findAll().stream()
//                .map(UserResponse::new)
//                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) throws IllegalArgumentException {
        //Optional<User> user = new User();
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateNickname(request.getNickname());
        //userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
