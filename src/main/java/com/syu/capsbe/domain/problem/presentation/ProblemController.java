package com.syu.capsbe.domain.problem.presentation;

import com.syu.capsbe.domain.problem.application.ProblemService;
import com.syu.capsbe.domain.problem.dto.request.ProblemRequestDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping("/api/v1/problem")
    public List<ProblemResponseDto> getProblems(@RequestBody ProblemRequestDto problemRequestDto) {
        return problemService.getProblemsByProblemType(problemRequestDto);
    }
}
