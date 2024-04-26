package com.syu.capsbe.domain.solveHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "문제 풀이 세부 기록 응답 정보")
public class SolveHistoryDetailResponse {

    @Schema(description = "문제", example = "Fill in the blank: 'Due to unforeseen circumstances, the meeting has been _______.' Choices: a) canceled, b) advanced, c) delayed, d) extended")
    private String question;

    @Schema(description = "정답", example = "c")
    private String answer;

    @Schema(description = "유저 제출 정답", example = "c")
    private String userAnswer;

    @Schema(description = "정답 여부", example = "true")
    private boolean isCorrect;

    public static SolveHistoryDetailResponse of(String question, String answer, String userAnswer, boolean isCorrect) {
        return SolveHistoryDetailResponse.builder()
                .question(question)
                .answer(answer)
                .userAnswer(userAnswer)
                .isCorrect(isCorrect)
                .build();
    }
}
