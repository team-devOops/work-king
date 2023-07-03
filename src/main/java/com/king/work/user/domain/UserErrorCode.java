package com.king.work.user.domain;

public enum UserErrorCode {
    INVALID_NICKNAME("닉네임은 필수입니다."),
    INVALID_PASSWORD("비밀번호는 필수입니다."),
    INVALID_OFFICE_HOURS("근무시간은 0보다 작을 수 없습니다.");

    private String message;

    UserErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
