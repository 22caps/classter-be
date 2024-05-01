package com.syu.capsbe.domain.problem.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "문제 힌트 요청 정보")
public class ProblemHintRequestDto {

    @NotNull
    @Schema(description = "문제 번호", example = "1")
    private Long problemId;
}
