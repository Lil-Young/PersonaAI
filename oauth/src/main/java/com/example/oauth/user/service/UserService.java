package com.example.oauth.user.service;

import com.example.oauth.user.domain.User;
import com.example.oauth.user.dto.UserCreateDto;
import com.example.oauth.user.dto.UserLoginDto;
import com.example.oauth.user.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(UserCreateDto userCreateDto) {
        User user = User.builder()
                .email(userCreateDto.getEmail())
                .password(passwordEncoder.encode(userCreateDto.getPassword()))
                .build();
        userRepository.save(user);
        return user;
    }

    public User login(UserLoginDto userLoginDto) {
        // Optional 객체는 User 객체가 있을수도 있고, 없을수도 있다는 의미를 가지고 있다. 그래서 Optinal 객체에는 있는지, 없는지 판단하는 변수가 있다.
        Optional<User> optUser = userRepository.findByEmail(userLoginDto.getEmail());
        if(!optUser.isPresent()){
            throw new IllegalArgumentException("email이 존재하지 않습니다.");
        }

        User user = optUser.get();
        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("password가 일치하지 않습니다.");
        }
        return user;
    }
}
