package com.king.work.user.domain;


import static com.king.work.user.domain.UserErrorCode.*;

import java.util.UUID;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    private UUID id;
    private String nickname;
    private int officeHours;
    private String password;

    public static final int DEFAULT_OFFICE_HOURS = 8;

    @Builder
    private User(String nickname, int officeHours, String password) {
        if (ObjectUtils.isEmpty(nickname)) {
            throw new UserValidationException(INVALID_NICKNAME);
        }

        if (ObjectUtils.isEmpty(password)) {
            throw new UserValidationException(INVALID_PASSWORD);
        }

        if (officeHours < 0) {
            throw new UserValidationException(INVALID_OFFICE_HOURS);
        }

        this.id = UUID.randomUUID();
        this.nickname = nickname;
        this.officeHours = officeHours;
        this.password = password;
    }

    public static User createUser(String nickname, String password, int officeHours) {
        return User.builder()
                .nickname(nickname)
                .officeHours(officeHours)
                .password(password)
                .build();
    }

    public static User createUser(String nickname, String password) {
        return User.builder()
                .nickname(nickname)
                .officeHours(DEFAULT_OFFICE_HOURS)
                .password(password)
                .build();
    }

}
