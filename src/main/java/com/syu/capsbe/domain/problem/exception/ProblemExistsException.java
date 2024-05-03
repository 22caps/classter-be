package com.syu.capsbe.domain.problem.exception;

import com.syu.capsbe.domain.problem.exception.common.ProblemErrorCode;
import lombok.Getter;

@Getter
public class ProblemExistsException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private ProblemExistsException(ProblemErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static ProblemExistsException of(ProblemErrorCode errorCode) {
        return new ProblemExistsException(errorCode);
    }
}
