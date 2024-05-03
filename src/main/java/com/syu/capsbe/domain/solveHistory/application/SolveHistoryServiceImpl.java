package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.application.MemberService;
import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.ProblemRepository;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.solveHistory.SolveHistory;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetail;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetailRepository;
import com.syu.capsbe.domain.solveHistory.SolveHistoryRepository;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
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
    //    private final ProblemService problemService;
    private final ProblemRepository problemRepository;
    private final SolveHistoryRepository solveHistoryRepository;
    private final SolveHistoryDetailRepository solveHistoryDetailRepository;

    @Override
    @Transactional
    public SubmissionResponseDto submitSolveHistory(SolveHistoryDetailRequestDto request,
            Long memberId) {
        // 사용자가 제출한 문제 풀이 기록을 저장
        // 각 문제별로 저장하되, 문제 풀이 시작(문제 리스트 가져오기) 시 설정한 solveCount로 세트를 묶는다.
        // 반환 시 정답 여부를 반환하게 한다.
        Member member = memberService.findByMemberId(memberId);
        Long solveCount = member.getSolveCount();
        Long problemId = request.getProblemId();

        Problem problem = problemRepository.findById(problemId)
                .orElseThrow(() -> SolveHistoryExistsException.of(
                        SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryId(memberId,
                        solveCount)
                .orElseThrow(() -> SolveHistoryExistsException.of(
                        SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

        boolean isCorrect = isCorrectAnswer(problem.getAnswer(), request.getUserAnswer());

        SolveHistoryDetail solveHistoryDetail = new SolveHistoryDetail(solveHistory, problem,
                request.getUserAnswer(), isCorrect);

        solveHistoryDetailRepository.save(solveHistoryDetail);

        checkSolverHistoryComplete(solveHistory, request.getProblemNumber(),
                request.getLastProblemNumber());

        return SubmissionResponseDto.of(isCorrect);
    }

    private boolean isCorrectAnswer(String answer, String userAnswer) {
        return answer.equals(userAnswer);
    }

    private void checkSolverHistoryComplete(SolveHistory solveHistory, int problemNumber,
            int lastProblemNumber) {
        if (problemNumber == lastProblemNumber) {
            solveHistory.completeSolveHistory();
        }
    }

    @Override
    @Transactional
    public void setSolveHistory(Member member, Long solveCount, ProblemType problemType) {
        // 문제 요청 시 껍데기 저장
        SolveHistory solveHistory = new SolveHistory(member, solveCount, problemType,
                LocalDateTime.now());

        solveHistoryRepository.save(solveHistory);
    }

    @Override
    public List<SolveHistoryResponseDto> getHistoryList(Long memberId) {
        List<SolveHistory> solveHistoryList = solveHistoryRepository.findByMemberIdAndIsCompletedIsTrue(
                memberId);

        return convertSolveHistoryEntityToDto(solveHistoryList);
    }

    @Override
    public List<SolveHistoryDetailResponse> getHistoryDetails(Long memberId, Long solveCount) {
        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryIdAndIsCompletedIsTrue(
                        memberId, solveCount)
                .orElseThrow(() -> SolveHistoryExistsException.of(
                        SolveHistoryErrorCode.SOLVE_HISTORY_IS_NOT_EXISTS));

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
                    .question(detail.getProblem().getQuestion())
                    .answer(detail.getProblem().getAnswer())
                    .userAnswer(detail.getUserAnswer())
                    .isCorrect(detail.isCorrect())
                    .build();

            solveHistoryDetailResponseList.add(solveHistoryDetailResponse);
        }

        return solveHistoryDetailResponseList;
    }
}
