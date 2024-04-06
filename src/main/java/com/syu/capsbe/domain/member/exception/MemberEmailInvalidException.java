package com.syu.capsbe.domain.member.exception;

import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberEmailInvalidException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private MemberEmailInvalidException(MemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static MemberEmailInvalidException of(MemberErrorCode errorCode) {
        return new MemberEmailInvalidException(errorCode);
    }
}
