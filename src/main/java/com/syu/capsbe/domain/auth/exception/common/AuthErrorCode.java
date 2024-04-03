package com.syu.capsbe.domain.auth.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode {

    EMAIL_SENDING_ERROR("E0000", "이메일을 보내는 중 오류가 발생했습니다.");

    private final String errorCode;
    private final String errorMessage;
}
