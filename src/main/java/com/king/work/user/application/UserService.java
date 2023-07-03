package com.king.work.user.application;

import com.king.work.user.domain.User;
import com.king.work.user.domain.UserRepository;
import com.king.work.user.dto.UserLoginRequest;
import com.king.work.user.dto.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserLoginResponse loginUser(UserLoginRequest request) {
        User user = User.createUser(request.nickname(), request.password());

        final User loginUser = userRepository.save(user);

        final UserLoginResponse userLoginResponse = new UserLoginResponse(loginUser.getNickname(),
                loginUser.getPassword());

        return userLoginResponse;
    }
}
