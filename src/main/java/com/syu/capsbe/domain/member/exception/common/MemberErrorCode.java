package com.syu.capsbe.domain.member.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode {

    INVALID_EMAIL_FORMAT("E1001", "이메일 형식이 올바르지 않습니다."),
    MEMBER_IS_NOT_EXIST("E1002", "존재하지 않는 회원입니다."),
    MEMBER_IS_ALREADY_EXIST("E1003", "이미 존재하는 회원입니다."),
    INVALID_GOAL_SCORE("E1004", "목표 점수는 100점 이상 990점 이하로 설정해야 합니다.");

    private final String errorCode;
    private final String errorMessage;
}
