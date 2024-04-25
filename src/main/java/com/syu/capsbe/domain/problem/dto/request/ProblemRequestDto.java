package com.syu.capsbe.domain.problem.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProblemRequestDto {

    @NotNull
    private String problemType;

    @NotNull
    private int problemCount;
}
