package com.syu.capsbe.domain.problem.dto.response;

import com.syu.capsbe.domain.problem.Problem;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(description = "문제 응답 정보")
public class ProblemResponseDto {

    @Schema(description = "문제 유형", example = "WORD | CONVERSATION | GRAMMAR")
    private final String problemType;

    @Schema(description = "문제", example = "Could you please send me the report by 3 PM today? Sure, I'll ______ it to you before then. Options: a) email, b) describe, c) announce, d) discuss")
    private final String question;

    @Schema(description = "정답", example = "a) email")
    private final String answer;

    public static ProblemResponseDto of(Problem problem) {
        return new ProblemResponseDto(problem.getProblemType().name(), problem.getQuestion(),
                problem.getAnswer());
    }
}
