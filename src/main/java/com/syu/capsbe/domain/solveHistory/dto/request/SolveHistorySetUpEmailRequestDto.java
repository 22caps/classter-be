package com.syu.capsbe.domain.solveHistory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "문제 풀이 시작 요청 정보(문제 풀이 시작 시 사용자의 문제 풀이 기록을 생성합니다.)")
@NoArgsConstructor
@AllArgsConstructor
public class SolveHistorySetUpEmailRequestDto {

    @Schema(description = "회원 Email", example = "abc@test.com")
    private String email;

    @Schema(description = "문제 유형", example = "WORD | CONVERSATION | GRAMMAR")
    private String problemType;

    @Schema(description = "문제 개수", example = "5")
    private int problemCount;
}
