package com.syu.capsbe.domain.problem.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProblemErrorCode {

    PROBLEM_IS_NOT_EXISTS("E3001", "해당 문제 번호의 문제가 존재하지 않습니다.");

    private final String errorCode;
    private final String errorMessage;
}
