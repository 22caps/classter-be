package com.syu.capsbe.domain.solveHistory.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "문제 풀이 전체 기록 응답 정보")
public class SolveHistoryResponseDto {

    @Schema(description = "풀이 기록 번호 | 문제 리스트 번호", example = "3")
    private Long solveHistoryCount;

    @Schema(description = "풀이 세부 기록", example = "3")
    private List<SolveHistoryDetailResponse> solveHistoryDetail;

    @Builder
    public static SolveHistoryResponseDto of(Long solveHistoryCount,
            List<SolveHistoryDetailResponse> solveHistoryDetail) {
        return new SolveHistoryResponseDto(solveHistoryCount, solveHistoryDetail);
    }
}
