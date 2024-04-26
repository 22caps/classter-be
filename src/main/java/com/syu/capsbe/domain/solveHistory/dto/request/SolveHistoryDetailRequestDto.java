package com.syu.capsbe.domain.solveHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "풀이 세부 기록 요청 정보")
@AllArgsConstructor
public class SolveHistoryDetailRequestDto {

    private String question;

    private String answer;

    private String userAnswer;

    private boolean isCorrect;
}
