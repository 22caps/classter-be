package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryReviewRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistorySetUpEmailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistorySetUpRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryReviewResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistorySetUpResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SubmissionResponseDto;
import java.util.List;

public interface SolveHistoryService {

    SolveHistorySetUpResponseDto setSolveHistory(SolveHistorySetUpRequestDto request, Long memberId);

    SolveHistorySetUpResponseDto setSolveHistoryWithEmail(SolveHistorySetUpEmailRequestDto request);

    SubmissionResponseDto submitSolveHistory(SolveHistoryDetailRequestDto request, Long memberId);

    List<SolveHistoryResponseDto> getHistoryList(Long memberId);

    List<SolveHistoryDetailResponse> getHistoryDetails(Long memberId, Long solveHistoryId);

    List<SolveHistoryReviewResponseDto> reviewSolveHistory(Long id, Long solveHistoryId);

    SubmissionResponseDto submitReviewSolveHistory(SolveHistoryReviewRequestDto request);
}
