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

    @Schema(description = "문제 번호", example = "1")
    private Long problemId;

    @Schema(description = "유저 제출 정답", example = "c")
    private String userAnswer;

    public SolveHistoryDetail toEntity(SolveHistory solveHistory) {
        return SolveHistoryDetail.builder()
                .solveHistory(solveHistory)
                .userAnswer(userAnswer)
                .build();
    }
}
