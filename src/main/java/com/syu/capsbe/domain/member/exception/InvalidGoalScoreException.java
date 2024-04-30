package com.syu.capsbe.domain.member.exception;

import com.syu.capsbe.domain.member.exception.common.MemberErrorCode;
import lombok.Getter;

@Getter
public class InvalidGoalScoreException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private InvalidGoalScoreException(MemberErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static InvalidGoalScoreException of(MemberErrorCode errorCode) {
        return new InvalidGoalScoreException(errorCode);
    }
}
