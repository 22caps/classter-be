package com.syu.capsbe.domain.solveHistory.application;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.member.MemberRepository;
import com.syu.capsbe.domain.problem.ProblemType;
import com.syu.capsbe.domain.solveHistory.SolveHistory;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetail;
import com.syu.capsbe.domain.solveHistory.SolveHistoryDetailRepository;
import com.syu.capsbe.domain.solveHistory.SolveHistoryRepository;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
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

    private final MemberRepository memberRepository;
    private final SolveHistoryRepository solveHistoryRepository;
    private final SolveHistoryDetailRepository solveHistoryDetailRepository;

    @Override
    @Transactional
    public void submitSolveHistory(SolveHistoryRequestDto request, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();

        member.updateSolveCount();

        Long solveCount = member.getSolveCount();

        // SolveHistoryRequestDto 내부 list로 존재하는 값을 저장하려고 해
        List<SolveHistoryDetailRequestDto> solveHistoryDetail = request.getSolveHistoryDetail();

        // solveHistoryDetail을 solveHistoryDetail로 엔티티 변환
        List<SolveHistoryDetail> solveHistoryDetailList = new ArrayList<>();

        // solveHistory 껍데기 저장
        ProblemType problemType = ProblemType.valueOf(request.getProblemType());

        LocalDateTime localDateTime = LocalDateTime.now();

        SolveHistory solveHistory = new SolveHistory(member, solveCount, problemType, localDateTime, solveHistoryDetailList);

        solveHistoryRepository.save(solveHistory);

        for (SolveHistoryDetailRequestDto dto : solveHistoryDetail) {
            // SolveHistoryDetail 엔티티 생성
            SolveHistoryDetail build = SolveHistoryDetail.builder()
                    .solveHistory(solveHistory)
                    .question(dto.getQuestion())
                    .answer(dto.getAnswer())
                    .userAnswer(dto.getUserAnswer())
                    .isCorrect(dto.isCorrect())
                    .build();

            solveHistory.addSolveHistoryDetail(build);
            solveHistoryDetailRepository.save(build);

            solveHistoryDetailList.add(build);
        }
    }

    @Override
    public List<SolveHistoryResponseDto> getHistoryList(Long memberId) {
        List<SolveHistory> byMemberId = solveHistoryRepository.findByMemberId(memberId);

        List<SolveHistoryResponseDto> solveHistoryResponseDtoList = new ArrayList<>();

        for (SolveHistory solveHistory : byMemberId) {
            List<SolveHistoryDetail> solveHistoryDetail = solveHistory.getSolveHistoryDetail();

            List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = new ArrayList<>();

            for (SolveHistoryDetail detail : solveHistoryDetail) {
                SolveHistoryDetailResponse solveHistoryDetailResponse = SolveHistoryDetailResponse.builder()
                        .question(detail.getQuestion())
                        .answer(detail.getAnswer())
                        .userAnswer(detail.getUserAnswer())
                        .isCorrect(detail.isCorrect())
                        .build();

                solveHistoryDetailResponseList.add(solveHistoryDetailResponse);
            }

            SolveHistoryResponseDto solveHistoryResponseDto = SolveHistoryResponseDto.builder()
                    .solveHistoryCount(solveHistory.getSolveCount())
                    .solveHistoryDetail(solveHistoryDetailResponseList)
                    .build();

            solveHistoryResponseDtoList.add(solveHistoryResponseDto);
        }

        return solveHistoryResponseDtoList;
    }

    @Override
    public List<SolveHistoryDetailResponse> getHistoryDetail(Long memberId, Long solveHistoryId) {

        SolveHistory solveHistory = solveHistoryRepository.findByMemberIdAndSolveHistoryId(memberId, solveHistoryId).orElseThrow();

        List<SolveHistoryDetail> solveHistoryDetail = solveHistory.getSolveHistoryDetail();

        List<SolveHistoryDetailResponse> solveHistoryDetailResponseList = new ArrayList<>();

        for (SolveHistoryDetail detail : solveHistoryDetail) {
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
