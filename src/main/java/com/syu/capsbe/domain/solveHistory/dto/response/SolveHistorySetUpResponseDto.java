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

    @Schema(description = "문제 개수", example = "5")
    private final int problemCount;

    public static SolveHistorySetUpResponseDto of(Long solveHistoryId, int problemCount) {
        return new SolveHistorySetUpResponseDto(solveHistoryId, problemCount);
    }
}
