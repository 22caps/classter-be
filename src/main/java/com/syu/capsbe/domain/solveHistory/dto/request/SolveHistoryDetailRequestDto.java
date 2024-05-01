package com.syu.capsbe.domain.solveHistory.dto.request;

import com.syu.capsbe.domain.solveHistory.SolveHistory;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "풀이 세부 기록 요청 정보")
@AllArgsConstructor
public class SolveHistoryDetailRequestDto {

    @Schema(description = "문제", example = "Fill in the blank: 'Due to unforeseen circumstances, the meeting has been _______.' Choices: a) canceled, b) advanced, c) delayed, d) extended")
    private String question;

    @Schema(description = "정답", example = "c")
    private String answer;

    @Schema(description = "유저 제출 정답", example = "c")
    private String userAnswer;

    public SolveHistoryDetail toEntity(SolveHistory solveHistory) {
        return SolveHistoryDetail.builder()
                .solveHistory(solveHistory)
                .question(question)
                .answer(answer)
                .userAnswer(userAnswer)
                .build();
    }
}
