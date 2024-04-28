package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.solveHistory.SolveHistory;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetail;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetailRepository;
import com.syu.capsbe.domain.solveHistory.SolveHistoryRepository;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import com.syu.capsbe.domain.solveHistory.exception.SolveHistoryExistsException;
import com.syu.capsbe.domain.solveHistory.exception.common.SolveHistoryErrorCode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SolveHistoryServiceImpl implements SolveHistoryService {

    private final MemberService memberService;
    private final SolveHistoryRepository solveHistoryRepository;
    private final SolveHistoryDetailRepository solveHistoryDetailRepository;

    @Override
    @Transactional
    public void submitSolveHistory(SolveHistoryRequestDto request, Long memberId) {
        Member member = memberService.findByMemberId(memberId);

        Long solveCount = member.updateSolveCount();

        SolveHistory solveHistory = new SolveHistory(member, solveCount,
                ProblemType.valueOf(request.getProblemType()), LocalDateTime.now());

        solveHistoryRepository.save(solveHistory);

        List<SolveHistoryDetailRequestDto> submittedSolveHistory = request.getSolveHistoryDetail();

        for (SolveHistoryDetailRequestDto dto : submittedSolveHistory) {
            solveHistoryDetailRepository.save(dto.toEntity(solveHistory));
        }
    }

    @Override
    public List<SolveHistoryResponseDto> getHistoryList(Long memberId) {
        List<SolveHistory> solveHistoryList = solveHistoryRepository.findByMemberId(memberId);

        return convertSolveHistoryEntityToDto(solveHistoryList);
    }

    @Override
    public List<SolveHistoryDetailResponse> getHistoryDetails(Long memberId, Long solveCount) {
        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryId(memberId, solveCount)
                .orElseThrow(() -> SolveHistoryExistsException.of(SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        return convertSolveHistoryDetailsEntityToDto(solveHistory);
    }

    private List<SolveHistoryResponseDto> convertSolveHistoryEntityToDto(
            List<SolveHistory> solveHistoryList) {
        List<SolveHistoryResponseDto> solveHistoryResponseDtoList = new ArrayList<>();

        for (SolveHistory solveHistory : solveHistoryList) {
            List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = convertSolveHistoryDetailsEntityToDto(
                    solveHistory);

            SolveHistoryResponseDto solveHistoryResponseDto = SolveHistoryResponseDto.builder()
                    .solveHistoryCount(solveHistory.getSolveCount())
                    .solveHistoryDetail(solveHistoryDetailResponseList)
                    .build();

            solveHistoryResponseDtoList.add(solveHistoryResponseDto);
        }

        return solveHistoryResponseDtoList;
    }

    private List<SolveHistoryDetailResponse> convertSolveHistoryDetailsEntityToDto(
            SolveHistory solveHistory) {
        List<SolveHistoryDetail> solveHistoryDetails = solveHistory.getSolveHistoryDetails();

        List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = new ArrayList<>();

        for (SolveHistoryDetail detail : solveHistoryDetails) {
            SolveHistoryDetailResponse solveHistoryDetailResponse = SolveHistoryDetailResponse.builder()
                    .question(detail.getQuestion())
                    .answer(detail.getAnswer())
                    .userAnswer(detail.getUserAnswer())
                    .isCorrect(detail.isCorrect())
                    .build();

            solveHistoryDetailResponseList.add(solveHistoryDetailResponse);
        }

        return solveHistoryDetailResponseList;
    }
}
