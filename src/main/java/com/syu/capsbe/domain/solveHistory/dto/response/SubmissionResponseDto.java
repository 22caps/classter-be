package com.syu.capsbe.domain.solveHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Schema(description = "문제 제출 응답 정보")
public class SubmissionResponseDto {

    @Schema(description = "정답 여부", example = "true")
    private final boolean isCorrect;

    @Schema(description = "문제 정답", example = "a")
    private final String problemAnswer;

    public static SubmissionResponseDto of(boolean isCorrect, String problemAnswer) {
        return new SubmissionResponseDto(isCorrect, problemAnswer);
    }
}
