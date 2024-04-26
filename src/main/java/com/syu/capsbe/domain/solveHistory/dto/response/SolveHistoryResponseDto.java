package com.syu.capsbe.domain.solveHistory.dto.response;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SolveHistoryResponseDto {

    private Long solveHistoryCount;

    private List<SolveHistoryDetailResponse> solveHistoryDetail = new ArrayList<>();

    @Builder
    public SolveHistoryResponseDto(Long solveHistoryCount, List<SolveHistoryDetailResponse> solveHistoryDetail) {
        this.solveHistoryCount = solveHistoryCount;
        this.solveHistoryDetail = solveHistoryDetail;
    }
}
