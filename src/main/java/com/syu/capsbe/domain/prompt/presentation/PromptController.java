package com.syu.capsbe.domain.prompt.presentation;

import com.syu.capsbe.domain.prompt.application.PromptService;
import com.syu.capsbe.domain.prompt.dto.response.PromptResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prompt")
@Tag(name = "Prompt", description = "프롬프트(ChatGPT) 도메인 API")
public class PromptController {

    private final PromptService promptService;

    @GetMapping
    @Operation(summary = "프롬프트", description = "GPT를 통해 프롬프트를 진행합니다.")
    @ApiResponse(responseCode = "201", description = "프롬프트 응답 성공")
    public PromptResponseDto prompt(@RequestParam("input") String input) {
        return promptService.getPromptResponse(input);
    }
}
