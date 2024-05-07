package com.syu.capsbe.global.exception;

import com.syu.capsbe.global.exception.common.GlobalErrorCode;
import lombok.Getter;

@Getter
public class TokenExpiredException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private TokenExpiredException(GlobalErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static TokenExpiredException of(GlobalErrorCode errorCode) {
        return new TokenExpiredException(errorCode);
    }
}
