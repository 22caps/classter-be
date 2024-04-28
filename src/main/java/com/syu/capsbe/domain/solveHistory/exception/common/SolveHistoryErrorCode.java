package com.syu.capsbe.domain.solveHistory.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SolveHistoryErrorCode {

    SOLVE_HISTORY_IS_NOT_EXISTS("E2001", "해당 문제 풀이 기록이 존재하지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
