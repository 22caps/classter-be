package com.syu.capsbe.domain.member.exception;

import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import lombok.Getter;

@Getter
public class MemberExistsException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private MemberExistsException(MemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static MemberExistsException of(MemberErrorCode errorCode) {
        return new MemberExistsException(errorCode);
    }
}
