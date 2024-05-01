package com.syu.capsbe.domain.problem.presentation;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.problem.application.ProblemService;
import com.syu.capsbe.domain.problem.dto.request.ProblemHintRequestDto;
import com.syu.capsbe.domain.problem.dto.request.ProblemRequestDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/problem")
@Tag(name = "Problem", description = "문제 도메인 API")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    @Operation(summary = "문제 조회", description = "문제 유형과 문제 개수를 통해 문제를 조회합니다. - 문제 풀이 시작")
    @ApiResponse(responseCode = "200", description = "문제 조회 성공")
    public List<ProblemResponseDto> getProblems(@AuthenticationPrincipal Member member,
            @RequestBody ProblemRequestDto problemRequestDto) {
        return problemService.getProblemsByProblemType(member.getId(), problemRequestDto);
    }

    @GetMapping("/hint")
    @Operation(summary = "힌트 조회", description = "문제 번호를 통해 문제에 대한 힌트를 LLM을 통해 요청하여 응답받습니다.")
    @ApiResponse(responseCode = "200", description = "힌트 조회 성공")
    @ApiResponse(responseCode = "E3001", description = "해당 문제 번호의 문제가 존재하지 않습니다.")
    public ProblemHintResponseDto getHint(
            @RequestBody ProblemHintRequestDto problemHintRequestDto) {
        return problemService.getHintByQuestion(problemHintRequestDto);
    }
}
