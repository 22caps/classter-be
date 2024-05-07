package com.syu.capsbe.domain.problem.application;

import com.syu.capsbe.domain.problem.Problem;
import com.syu.capsbe.domain.problem.dto.response.ProblemHintResponseDto;
import com.syu.capsbe.domain.problem.dto.response.ProblemResponseDto;
import java.util.List;

public interface ProblemService {

    List<ProblemResponseDto> getProblemsByProblemType(Long memberId, String problemTypeRequest,
            int problemCountRequest);

    ProblemHintResponseDto getHintByQuestion(Long problemId);

    Problem getProblemById(Long problemId);
}
