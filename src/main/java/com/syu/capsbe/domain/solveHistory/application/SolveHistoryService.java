package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import java.util.List;

public interface SolveHistoryService {

    void submitSolveHistory(SolveHistoryRequestDto request, Long memberId);

    List<SolveHistoryResponseDto> getHistoryList(Long memberId);

    List<SolveHistoryDetailResponse> getHistoryDetail(Long memberId, Long solveHistoryId);
}
