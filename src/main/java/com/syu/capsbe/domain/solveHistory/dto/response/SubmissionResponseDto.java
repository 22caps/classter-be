package com.syu.capsbe.domain.solveHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "문제 제출 응답 정보")
public class SubmissionResponseDto {

    private final boolean isCorrect;

    public static SubmissionResponseDto of(boolean isCorrect) {
        return new SubmissionResponseDto(isCorrect);
    }
}
