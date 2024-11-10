package com.syu.capsbe.domain.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "로그인 응답 정보, 사용자의 정답률 정보를 반환하도록")
@AllArgsConstructor
@NoArgsConstructor
public class SignInEmailResponseDto {

    // WORD, CONVERSATION, GRAMMAR 정답률 정보
    @Schema(description = "문법 문제 정답률", example = "0.5")
    private double grammarCorrectRate;

    @Schema(description = "회화 문제 정답률", example = "0.5")
    private double conversationCorrectRate;

    @Schema(description = "단어 문제 정답률", example = "0.5")
    private double wordCorrectRate;

    public static SignInEmailResponseDto of(double grammarCorrectRate, double conversationCorrectRate, double wordCorrectRate) {
        return new SignInEmailResponseDto(grammarCorrectRate, conversationCorrectRate, wordCorrectRate);
    }
}
