package com.syu.capsbe.global.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GlobalErrorCode {

    TOKEN_EXPIRED("E4001", "토큰이 만료되었습니다."),
    TOKEN_INVALID("E4002", "토큰이 유효하지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
