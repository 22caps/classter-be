package com.syu.capsbe.domain.solveHistory.exception;

import com.syu.capsbe.domain.solveHistory.exception.common.SolveHistoryErrorCode;
import lombok.Getter;

@Getter
public class SolveHistoryExistsException extends RuntimeException {

    private final String errorCode;
    private final String errorMessage;

    private SolveHistoryExistsException(SolveHistoryErrorCode errorCode) {
        this.errorCode = errorCode.getErrorCode();
        this.errorMessage = errorCode.getErrorMessage();
    }

    public static SolveHistoryExistsException of(SolveHistoryErrorCode errorCode) {
        return new SolveHistoryExistsException(errorCode);
    }
}
