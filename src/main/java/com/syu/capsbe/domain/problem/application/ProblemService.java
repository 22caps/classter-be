package com.syu.capsbe.domain.problem.application;

import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;

public interface ProblemService {

    ProblemResponseDto getProblemByProblemType(String problemTypeRequest);

    ProblemHintResponseDto getHintByQuestion(Long problemId);

    Problem getProblemById(Long problemId);

    ProblemResponseDto getGrammarProblem();
}
