package com.syu.capsbe.domain.solveHistory.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SolveHistoryDetailResponse {

    private String question;

    private String answer;

    private String userAnswer;

    private boolean isCorrect;

    @Builder
    public SolveHistoryDetailResponse(String question, String answer, String userAnswer, boolean isCorrect) {
        this.question = question;
        this.answer = answer;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }
}
