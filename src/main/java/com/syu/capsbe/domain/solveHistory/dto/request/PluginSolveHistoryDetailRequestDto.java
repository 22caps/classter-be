package com.syu.capsbe.domain.solveHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Schema(description = "플러그인 풀이 세부 기록 요청 정보")
@AllArgsConstructor
public class PluginSolveHistoryDetailRequestDto {

    @Schema(description = "유저 이메일", example = "abc@test.com")
    private String email;

    @Schema(description = "정답 여부", example = "true")
    private boolean isCorrect;
}
