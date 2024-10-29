package com.syu.capsbe.domain.problem.presentation;

import com.syu.capsbe.domain.member.Member;
import com.syu.capsbe.domain.problem.application.ProblemService;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/problem")
@Tag(name = "Problem", description = "문제 도메인 API")
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/random")
    @Operation(summary = "랜덤 문제 조회", description = "문제 유형을 통해 랜덤 문제를 조회합니다. 해당 요청은 문제 풀이 시마다 호출됩니다.")
    @ApiResponse(responseCode = "200", description = "문제 조회 성공")
    public ProblemResponseDto getRandomProblem() {
        return problemService.getRandomProblem();
    }


    @GetMapping("/{problemType}")
    @Operation(summary = "문제 조회", description = "문제 유형을 통해 문제를 조회합니다. 해당 요청은 문제 풀이 시마다 호출됩니다.")
    @ApiResponse(responseCode = "200", description = "문제 조회 성공")
    public ProblemResponseDto getProblem(
            @PathVariable("problemType") String problemType
    ) {
        return problemService.getProblemByProblemType(problemType);
    }

    @GetMapping("/hint/{problemId}")
    @Operation(summary = "힌트 조회", description = "문제 번호를 통해 문제에 대한 힌트를 LLM을 통해 요청하여 응답받습니다.")
    @ApiResponse(responseCode = "200", description = "힌트 조회 성공")
    @ApiResponse(responseCode = "E3001", description = "해당 문제 번호의 문제가 존재하지 않습니다.")
    public ProblemHintResponseDto getHint(@PathVariable("problemId") Long problemId) {
        return problemService.getHintByQuestion(problemId);
    }

    @GetMapping("/grammar")
    @Operation(summary = "문법 문제 조회", description = "gpt 플러그인용 테스트 API")
    @ApiResponse(responseCode = "200", description = "문제 조회 성공")
    public ProblemResponseDto getProblem(
    ) {
        return problemService.getGrammarProblem();
    }
}
