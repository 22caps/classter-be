package com.syu.capsbe.domain.problem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Schema(description = "문제 요청 정보")
public class ProblemRequestDto {

    @NotNull
    @Schema(description = "문제 유형", example = "WORD | CONVERSATION | GRAMMAR")
    private String problemType;

    @NotNull
    @Schema(description = "문제 개수", example = "3 | 5 | 10")
    private int problemCount;
}
