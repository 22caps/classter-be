package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.ProblemRepository;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.problem.exception.ProblemExistsException;
import com.syu.capsbe.domain.problem.exception.common.ProblemErrorCode;
import com.syu.capsbe.domain.solveHistory.SolveHistory;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetail;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetailRepository;
import com.syu.capsbe.domain.solveHistory.SolveHistoryRepository;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistorySetUpRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistorySetUpResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SubmissionResponseDto;
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
    private final ProblemRepository problemRepository;
    private final SolveHistoryRepository solveHistoryRepository;
    private final SolveHistoryDetailRepository solveHistoryDetailRepository;

    @Override
    @Transactional
    public SolveHistorySetUpResponseDto setSolveHistory(SolveHistorySetUpRequestDto request, Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        member.updateSolveCount();

        Long solveCount = member.getSolveCount();
        ProblemType problemType = ProblemType.valueOf(request.getProblemType());

        SolveHistory solveHistory = solveHistoryRepository.save(
                new SolveHistory(member, solveCount, problemType, LocalDateTime.now())
        );

        return SolveHistorySetUpResponseDto.of(solveHistory.getId());
    }

    @Override
    @Transactional
    public SubmissionResponseDto submitSolveHistory(SolveHistoryDetailRequestDto request, Long memberId) {
        Member member = memberService.findByMemberId(memberId);
        Long solveCount = member.getSolveCount();

        Long problemId = request.getProblemId();
        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> ProblemExistsException.of(ProblemErrorCode.PROBLEM_IS_NOT_EXISTS));

        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryId(memberId, solveCount)
                .orElseThrow(() -> SolveHistoryExistsException.of(SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        boolean isCorrect = isCorrectAnswer(problem.getAnswer(), request.getUserAnswer());

        solveHistoryDetailRepository.save(
                new SolveHistoryDetail(solveHistory, problem, request.getUserAnswer(), isCorrect)
        );

        checkSolverHistoryComplete(solveHistory, request.getProblemNumber(), request.getLastProblemNumber());

        return SubmissionResponseDto.of(isCorrect);
    }

    private boolean isCorrectAnswer(String answer, String userAnswer) {
        return answer.equals(userAnswer);
    }

    private void checkSolverHistoryComplete(SolveHistory solveHistory, int problemNumber, int lastProblemNumber) {
        if (problemNumber == lastProblemNumber) solveHistory.completeSolveHistory();
    }

    @Override
    public List<SolveHistoryResponseDto> getHistoryList(Long memberId) {
        return convertSolveHistoryEntityToDto(solveHistoryRepository.findByMemberIdAndIsCompletedIsTrue(memberId));
    }

    @Override
    public List<SolveHistoryDetailResponse> getHistoryDetails(Long memberId, Long solveCount) {
        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryIdAndIsCompletedIsTrue(memberId, solveCount)
                .orElseThrow(() -> SolveHistoryExistsException.of(SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        return convertSolveHistoryDetailsEntityToDto(solveHistory);
    }

    private List<SolveHistoryResponseDto> convertSolveHistoryEntityToDto(List<SolveHistory> solveHistoryList) {
        List<SolveHistoryResponseDto> solveHistoryResponseDtoList = new ArrayList<>();

        for (SolveHistory solveHistory : solveHistoryList) {
            List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = convertSolveHistoryDetailsEntityToDto(solveHistory);

            SolveHistoryResponseDto solveHistoryResponseDto = SolveHistoryResponseDto.builder()
                    .solveHistoryCount(solveHistory.getSolveCount())
                    .solveHistoryDetail(solveHistoryDetailResponseList)
                    .build();

            solveHistoryResponseDtoList.add(solveHistoryResponseDto);
        }

        return solveHistoryResponseDtoList;
    }

    private List<SolveHistoryDetailResponse> convertSolveHistoryDetailsEntityToDto(SolveHistory solveHistory) {
        List<SolveHistoryDetail> solveHistoryDetails = solveHistory.getSolveHistoryDetails();

        List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = new ArrayList<>();

        for (SolveHistoryDetail detail : solveHistoryDetails) {
            SolveHistoryDetailResponse solveHistoryDetailResponse = SolveHistoryDetailResponse.builder()
                    .question(detail.getProblem().getQuestion())
                    .choices(detail.getProblem().convertChoicesToList())
                    .answer(detail.getProblem().getAnswer())
                    .userAnswer(detail.getUserAnswer())
                    .isCorrect(detail.isCorrect())
                    .build();

            solveHistoryDetailResponseList.add(solveHistoryDetailResponse);
        }

        return solveHistoryDetailResponseList;
    }
}
