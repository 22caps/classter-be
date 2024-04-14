package com.syu.capsbe.domain.prompt.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Schema(description = "프롬프트 요청 정보")
@AllArgsConstructor
@NoArgsConstructor
public class PromptRequestDto {

    @Schema(description = "프롬프트 요청 텍스트", example = "서울의 수도는 무엇인가요")
    private String input;
}
