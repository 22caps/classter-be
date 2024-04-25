package com.syu.capsbe.domain.problem.presentation;

import com.syu.capsbe.domain.problem.application.ProblemService;
import com.syu.capsbe.domain.problem.dto.request.ProblemRequestDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
    @Operation(summary = "문제 조회", description = "문제 유형과 문제 개수를 통해 문제를 조회합니다.")
    @ApiResponse(responseCode = "200", description = "문제 조회 성공")
    public List<ProblemResponseDto> getProblems(@RequestBody ProblemRequestDto problemRequestDto) {
        return problemService.getProblemsByProblemType(problemRequestDto);
    }
}
