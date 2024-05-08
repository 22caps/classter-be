package com.syu.capsbe.domain.solveHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "문제 풀이 시작 응답 정보")
public class SolveHistorySetUpResponseDto {

    @Schema(description = "풀이 기록 번호", example = "1")
    private final Long solveHistoryId;

    public static SolveHistorySetUpResponseDto of(Long solveHistoryId) {
        return new SolveHistorySetUpResponseDto(solveHistoryId);
    }
}
