package com.syu.capsbe.domain.member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {

    INVALID_EMAIL_FORMAT("E1001", "이메일 형식이 올바르지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
