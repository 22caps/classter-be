package com.syu.capsbe.domain.prompt.application;

import com.syu.capsbe.domain.prompt.dto.response.PromptResponseDto;

public interface PromptService {

    PromptResponseDto getPromptResponse(String content);
}
