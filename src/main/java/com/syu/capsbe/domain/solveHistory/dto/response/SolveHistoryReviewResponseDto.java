package com.syu.capsbe.domain.solveHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "복습 제출 응답 정보")
public class SolveHistoryReviewResponseDto {

    @Schema(description = "문제 번호(데이터베이스에 저장된 번호)", example = "1")
    private final Long problemId;

    @Schema(description = "문제", example = "Could you please send me the report by 3 PM today? Sure, I'll ______ it to you before then.")
    private final String question;

    @Schema(description = "선택지", example = "a) email, b) describe, c) announce, d) discuss")
    private final List<String> choices;

    public static SolveHistoryReviewResponseDto of(Long problemId, String question, List<String> choices) {
        return new SolveHistoryReviewResponseDto(problemId, question, choices);
    }
}
