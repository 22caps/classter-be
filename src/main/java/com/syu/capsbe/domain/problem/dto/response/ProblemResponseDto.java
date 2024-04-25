package com.syu.capsbe.domain.problem.dto.response;

import com.syu.capsbe.domain.problem.Problem;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ProblemResponseDto {

    private final String problemType;

    private final String question;

    private final String answer;

    public static ProblemResponseDto of(Problem problem) {
        return new ProblemResponseDto(problem.getProblemType().name(), problem.getQuestion(),
                problem.getAnswer());
    }
}
