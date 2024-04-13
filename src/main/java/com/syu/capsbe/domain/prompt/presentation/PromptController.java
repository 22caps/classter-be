package com.syu.capsbe.domain.prompt.presentation;

import com.syu.capsbe.domain.prompt.application.PromptService;
import com.syu.capsbe.domain.prompt.dto.response.PromptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prompt")
public class PromptController {

    private final PromptService promptService;

    @GetMapping
    public PromptResponseDto prompt(@RequestParam("input") String input) {
        return promptService.getPromptResponse(input);
    }
}
