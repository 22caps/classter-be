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
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryReviewRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistorySetUpEmailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistorySetUpRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryReviewResponseDto;
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

        ProblemType problemType = ProblemType.valueOf(request.getProblemType());

        SolveHistory solveHistory = solveHistoryRepository.save(
                new SolveHistory(member, problemType, LocalDateTime.now())
        ); // SolveHistory 생성

        return SolveHistorySetUpResponseDto.of(solveHistory.getId(), request.getProblemCount());
    }

    @Override
    public SolveHistorySetUpResponseDto setSolveHistoryWithEmail(
            SolveHistorySetUpEmailRequestDto request) {
        Member member = memberService.findByEmail(request.getEmail()).orElseThrow();

        SolveHistory solveHistory = solveHistoryRepository.save(
                new SolveHistory(member, ProblemType.valueOf(request.getProblemType()), LocalDateTime.now())
        ); // SolveHistory 생성

        return SolveHistorySetUpResponseDto.of(solveHistory.getId(), request.getProblemCount());
    }

    @Override
    @Transactional
    public SubmissionResponseDto submitSolveHistory(SolveHistoryDetailRequestDto request, Long memberId) {
        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> ProblemExistsException.of(ProblemErrorCode.PROBLEM_IS_NOT_EXISTS));

        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryId(memberId, request.getSolveHistoryId())
                .orElseThrow(() -> SolveHistoryExistsException.of(SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        boolean isCorrect = isCorrectAnswer(problem.getAnswer(), request.getUserAnswer());

        solveHistoryDetailRepository.save(
                new SolveHistoryDetail(solveHistory, problem, request.getUserAnswer(), isCorrect)
        ); // SolveHistoryDetail 생성

        checkSolverHistoryComplete(solveHistory, request.getProblemNumber(), request.getLastProblemNumber());

        return SubmissionResponseDto.of(isCorrect, problem.getAnswer());
    }

    @Override
    public SubmissionResponseDto submitPluginSolveHistory(SolveHistoryDetailRequestDto request) {
        Problem problem = problemRepository.findById(request.getProblemId())
                .orElseThrow(() -> ProblemExistsException.of(ProblemErrorCode.PROBLEM_IS_NOT_EXISTS));

        boolean isCorrect = isCorrectAnswer(problem.getAnswer(), request.getUserAnswer());

        return SubmissionResponseDto.of(isCorrect, problem.getAnswer());
    }

    @Override
    public List<SolveHistoryResponseDto> getHistoryList(Long memberId) {
        return convertSolveHistoryEntityToDto(solveHistoryRepository.findByMemberIdAndIsCompletedIsTrue(memberId));
    }

    @Override
    public List<SolveHistoryDetailResponse> getHistoryDetails(Long memberId, Long solveHistoryId) {
        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryIdAndIsCompletedIsTrue(memberId, solveHistoryId)
                .orElseThrow(() -> SolveHistoryExistsException.of(SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        return convertSolveHistoryDetailsEntityToDto(solveHistory);
    }

    @Override
    @Transactional
    public List<SolveHistoryReviewResponseDto> reviewSolveHistory(Long id, Long solveHistoryId) {
        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryIdAndIsCompletedIsTrue(id, solveHistoryId)
                .orElseThrow(() -> SolveHistoryExistsException.of(SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        solveHistory.reviewSolveHistory();

        // solveHistory에서 틀린 문제들만 추출
        List<SolveHistoryDetail> wrongSolveHistoryDetails = solveHistory.getSolveHistoryDetails().stream()
                .filter(solveHistoryDetail -> !solveHistoryDetail.isCorrect())
                .toList();

        // SolveHistoryDetail에 problemId를 통해 문제 List 추출
        List<Problem> wrongProblems = wrongSolveHistoryDetails.stream()
                .map(SolveHistoryDetail::getProblem)
                .toList();

        // List<Problem>를 List<SolveHistoryReviewResponseDto>로 변환
        List<SolveHistoryReviewResponseDto> solveHistoryReviewResponseDtoList = new ArrayList<>();
        for (Problem problem : wrongProblems) {
            solveHistoryReviewResponseDtoList.add(
                    SolveHistoryReviewResponseDto.of(problem.getId(), problem.getQuestion(), problem.convertChoicesToList())
            );
        }

        return solveHistoryReviewResponseDtoList;
    }

    @Override
    public SubmissionResponseDto submitReviewSolveHistory(SolveHistoryReviewRequestDto request) {
        Problem problem = problemRepository.findProblemById(request.getProblemId())
                .orElseThrow(
                        () -> ProblemExistsException.of(ProblemErrorCode.PROBLEM_IS_NOT_EXISTS));

        boolean isCorrect = isCorrectAnswer(problem.getAnswer(), request.getUserAnswer());

        return SubmissionResponseDto.of(isCorrect, problem.getAnswer());
    }

    private boolean isCorrectAnswer(String answer, String userAnswer) {
        return answer.equals(userAnswer);
    }

    private void checkSolverHistoryComplete(SolveHistory solveHistory, int problemNumber, int lastProblemNumber) {
        if (solveHistory.getSolveHistoryDetailsSize() > lastProblemNumber)
            throw new IllegalArgumentException("문제 풀이가 이미 완료됐어야 하는 상태입니다.");

        if (problemNumber == lastProblemNumber) {
            solveHistory.completeSolveHistory();

            double correctRate = solveHistory.getSolveHistoryDetails().stream()
                    .filter(SolveHistoryDetail::isCorrect)
                    .count() / (double) lastProblemNumber * 100;
            solveHistory.updateCorrectRate(correctRate);

            if ((int) correctRate == 100) {
                solveHistory.reviewSolveHistory();
            }
        }
    }

    private List<SolveHistoryResponseDto> convertSolveHistoryEntityToDto(List<SolveHistory> solveHistoryList) {
        List<SolveHistoryResponseDto> solveHistoryResponseDtoList = new ArrayList<>();

        for (SolveHistory solveHistory : solveHistoryList) {
            List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = convertSolveHistoryDetailsEntityToDto(solveHistory);

            SolveHistoryResponseDto solveHistoryResponseDto = SolveHistoryResponseDto.builder()
                    .solveHistoryId(solveHistory.getId())
                    .solveHistoryDetail(solveHistoryDetailResponseList)
                    .problemType(solveHistory.getProblemType())
                    .correctRate(solveHistory.getCorrectRate())
                    .solveDate(solveHistory.getSolveDate())
                    .isReviewed(solveHistory.isReviewed())
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
