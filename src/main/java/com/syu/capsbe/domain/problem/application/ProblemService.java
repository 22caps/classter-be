package com.syu.capsbe.domain.problem.application;

import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.dto.request.ProblemHintRequestDto;
import com.syu.capsbe.domain.problem.dto.request.ProblemRequestDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import java.util.List;

public interface ProblemService {

    List<ProblemResponseDto> getProblemsByProblemType(Long memberId, ProblemRequestDto problemRequestDto);

    ProblemHintResponseDto getHintByQuestion(ProblemHintRequestDto problemHintRequestDto);

    Problem getProblemById(Long problemId);
}
