package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SubmissionResponseDto;
import java.util.List;

public interface SolveHistoryService {

    SubmissionResponseDto submitSolveHistory(SolveHistoryDetailRequestDto request, Long memberId);

    void setSolveHistory(Member member, Long solveCount, ProblemType problemType);

    List<SolveHistoryResponseDto> getHistoryList(Long memberId);

    List<SolveHistoryDetailResponse> getHistoryDetails(Long memberId, Long solveCount);
}
