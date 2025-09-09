package com.example.oauth.user.controller;

import com.example.oauth.common.auth.JwtTokenProvider;
import com.example.oauth.user.domain.User;
import com.example.oauth.user.dto.UserCreateDto;
import com.example.oauth.user.dto.UserLoginDto;
import com.example.oauth.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user")
@RestController
public class UserController {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/create")
    public ResponseEntity<?> userCreate(@RequestBody UserCreateDto userCreateDto) {
        System.out.println(userCreateDto.getEmail() + " " + userCreateDto.getPassword());
        User user = userService.create(userCreateDto);
        return new ResponseEntity<>(user.getId(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody UserLoginDto userLoginDto) {
//        email, password 일치하는지 검증
        User user = userService.login(userLoginDto);

//        일치할 경우 jwt accesstoken 및 refreshtoken 생성
        String jwtToken = jwtTokenProvider.createToken(user.getEmail(), user.getRole().toString());

        Map<String, Object> loginInfo = new HashMap<>();
        loginInfo.put("id", user.getId());
        loginInfo.put("token", jwtToken);
        return new ResponseEntity<>(loginInfo, HttpStatus.OK);
    }
}
