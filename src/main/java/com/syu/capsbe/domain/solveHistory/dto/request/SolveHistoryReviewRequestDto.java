package com.syu.capsbe.domain.solveHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "복습 요청 정보")
@AllArgsConstructor
public class SolveHistoryReviewRequestDto {

    @Schema(description = "문제 번호(데이터베이스에 저장된 번호)", example = "1")
    private Long problemId;

    @Schema(description = "유저 제출 정답", example = "c")
    private String userAnswer;
}
