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
    @Schema(description = "문제", example = "Fill in the blank: 'Due to unforeseen circumstances, the meeting has been _______.' Choices: a) canceled, b) advanced, c) delayed, d) extended")
    private String question;
}
