package com.king.work.user.domain;

public class UserValidationException extends IllegalArgumentException {
    private UserErrorCode errorCode;

    public UserValidationException(UserErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public UserErrorCode getErrorCode() {
        return errorCode;
    }
}
