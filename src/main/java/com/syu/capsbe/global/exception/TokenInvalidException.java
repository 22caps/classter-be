package com.syu.capsbe.global.exception;

import com.syu.capsbe.global.exception.common.GlobalErrorCode;
import lombok.Getter;

@Getter
public class TokenInvalidException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private TokenInvalidException(GlobalErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static TokenInvalidException of(GlobalErrorCode errorCode) {
        return new TokenInvalidException(errorCode);
    }
}
