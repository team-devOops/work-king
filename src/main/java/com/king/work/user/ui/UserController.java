package com.king.work.user.ui;

import com.king.work.user.application.UserService;
import com.king.work.user.dto.UserLoginRequest;
import com.king.work.user.dto.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(UserLoginRequest request) {

        return ResponseEntity.ok(userService.loginUser(request));

    }
}
