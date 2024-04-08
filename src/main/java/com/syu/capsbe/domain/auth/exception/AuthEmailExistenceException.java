package com.syu.capsbe.domain.auth.exception;

import com.syu.capsbe.domain.auth.exception.common.AuthErrorCode;
import lombok.Getter;

@Getter
public class AuthEmailExistenceException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private AuthEmailExistenceException(AuthErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static AuthEmailExistenceException of(AuthErrorCode errorCode) {
        return new AuthEmailExistenceException(errorCode);
    }

}
