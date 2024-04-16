package com.syu.capsbe.domain.prompt.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Schema(description = "프롬프트 응답 정보")
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PromptResponseDto {

    @Schema(description = "GPT 응답 데이터", example = "한국의 수도는 서울입니다.")
    private final String content;

    public static PromptResponseDto of(String content) {
        return new PromptResponseDto(content);
    }
}
