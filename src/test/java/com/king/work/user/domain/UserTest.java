package com.king.work.user.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

class UserTest {

    @Test
    @DisplayName("유저가 생성 된다.")
    void createUser() {
        // given
        User user = User.createUser("test", "test", 20);

        // when & then
        assertSoftly(softly -> {
            softly.assertThat(user.getNickname()).isEqualTo("test");
            softly.assertThat(user.getOfficeHours()).isEqualTo(20);
            softly.assertThat(user.getPassword()).isEqualTo("test");
            softly.assertThat(user.getId()).isNotNull();
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("유저가 생성 될때 닉네임은 필수이다.")
    void createUserWithoutNickname(String nickname) {
        assertThatIllegalArgumentException().isThrownBy(() ->
            User.createUser(nickname, "test")
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("유저가 생성 될때 비밀번호는 필수이다.")
    void createUserWithoutPassword(String password) {
        assertThatIllegalArgumentException().isThrownBy(() ->
            User.createUser("test", password)
        );
    }

    @Test
    @DisplayName("유저가 생성될때 근무시간은 0보다 작을수 없다")
    void createUserWithNegativeOfficeHours() {
        assertThatIllegalArgumentException().isThrownBy(() ->
            User.createUser("test", "test", -1)
        );
    }

}