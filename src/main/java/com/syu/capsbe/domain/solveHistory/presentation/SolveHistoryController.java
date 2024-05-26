package com.syu.capsbe.domain.solveHistory.presentation;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.solveHistory.application.SolveHistoryService;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryDetailRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryReviewRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistorySetUpRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryReviewResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistorySetUpResponseDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SubmissionResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/solve-history")
@Tag(name = "SolveHistory", description = "문제 풀이 기록 API")
public class SolveHistoryController {

    private final SolveHistoryService solveHistoryService;

    @PostMapping("/start")
    @Operation(summary = "문제 풀이 시작", description = "문제 풀이를 시작합니다. 문제 풀이 시작 시 사용자의 문제 풀이 기록을 생성합니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 시작 성공")
    public SolveHistorySetUpResponseDto startSolveHistory(
            @AuthenticationPrincipal Member member,
            @RequestBody SolveHistorySetUpRequestDto request) {
        return solveHistoryService.setSolveHistory(request, member.getId());
    }

    @PostMapping("/submit")
    @Operation(summary = "문제 풀이 기록 제출", description = "문제 풀이 기록을 제출합니다. 사용자는 모든 문제를 푼 후에 제출할 수 있습니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 기록 제출 성공")
    public SubmissionResponseDto submitSolveHistory(
            @RequestBody SolveHistoryDetailRequestDto request,
            @AuthenticationPrincipal Member member) {
        return solveHistoryService.submitSolveHistory(request, member.getId());
    }

    @GetMapping("/list")
    @Operation(summary = "문제 풀이 기록 전체 조회", description = "사용자의 문제 풀이 기록을 조회합니다. 풀이 완료가 된 문제들만 조회 가능합니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 기록 전체 조회 성공")
    public List<SolveHistoryResponseDto> getHistoryList(@AuthenticationPrincipal Member member) {
        return solveHistoryService.getHistoryList(member.getId());
    }

    @GetMapping("/detail/{solveHistoryId}")
    @Operation(summary = "문제 풀이 기록 세부 조회", description = "사용자의 문제 풀이 기록 세부를 조회합니다. 문제 풀이 기록 번호를 통해 세부 조회가 가능합니다. 풀이 완료가 된 문제만 조회 가능합니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 기록 세부 조회 성공")
    @ApiResponse(responseCode = "E2001", description = "해당 문제 풀이 기록이 존재하지 않습니다.")
    public List<SolveHistoryDetailResponse> getHistoryDetails(
            @AuthenticationPrincipal Member member,
            @PathVariable Long solveHistoryId) {
        return solveHistoryService.getHistoryDetails(member.getId(), solveHistoryId);
    }

    @PostMapping("/review/{solveHistoryId}")
    @Operation(summary = "문제 풀이 복습", description = "문제 풀이 기록 번호를 통해 복습이 가능합니다. 풀이 완료인 문제 풀이 중 틀린 문제를 반환합니다.")
    @ApiResponse(responseCode = "200", description = "문제 풀이 복습 성공")
    @ApiResponse(responseCode = "E2001", description = "해당 문제 풀이 기록이 존재하지 않습니다.")
    public List<SolveHistoryReviewResponseDto> reviewSolveHistory(
            @AuthenticationPrincipal Member member,
            @PathVariable Long solveHistoryId) {
        return solveHistoryService.reviewSolveHistory(member.getId(), solveHistoryId);
    }

    @GetMapping("/review/submit")
    @Operation(summary = "문제 복습 중 풀이 제출", description = "문제 복습 중 풀이를 제출합니다. 해당 정보는 저장되지 않고, 채점 결과만 반환하도록 합니다.")
    @ApiResponse(responseCode = "200", description = "문제 복습 중 풀이 제출 성공")
    public SubmissionResponseDto submitReviewSolveHistory(
            @RequestBody SolveHistoryReviewRequestDto request) {
        return solveHistoryService.submitReviewSolveHistory(request);
    }
}
