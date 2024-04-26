package com.syu.capsbe.domain.solveHistory.presentation;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.solveHistory.application.SolveHistoryService;
import com.syu.capsbe.domain.solveHistory.dto.request.SolveHistoryRequestDto;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryDetailResponse;
import com.syu.capsbe.domain.solveHistory.dto.response.SolveHistoryResponseDto;
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
public class SolveHistoryController {

    private final SolveHistoryService solveHistoryService;

    @PostMapping("/submit")
    public void submitSolveHistory(@RequestBody SolveHistoryRequestDto request,
            @AuthenticationPrincipal Member member) {
        solveHistoryService.submitSolveHistory(request, member.getId());
    }

    @GetMapping("/list")
    public List<SolveHistoryResponseDto> getHistoryList(@AuthenticationPrincipal Member member) {
        return solveHistoryService.getHistoryList(member.getId());
    }

    @GetMapping("/detail/{solveHistoryId}")
    public List<SolveHistoryDetailResponse> getHistoryDetail(@AuthenticationPrincipal Member member,
            @PathVariable Long solveHistoryId) {
        return solveHistoryService.getHistoryDetail(member.getId(), solveHistoryId);
    }
}
