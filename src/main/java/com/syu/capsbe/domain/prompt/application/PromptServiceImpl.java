package com.syu.capsbe.domain.prompt.application;

import com.syu.capsbe.domain.prompt.dto.request.GptRequestDto;
import com.syu.capsbe.domain.prompt.dto.response.GptResponseDto;
import com.syu.capsbe.domain.prompt.dto.response.PromptResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PromptServiceImpl implements PromptService {

    private final RestTemplate restTemplate;

    @Value("${OPENAI.MODEL}")
    private String model;

    @Value("${OPENAI.API_URL}")
    private String apiUrl;

    @Override
    public PromptResponseDto getPromptResponse(String input) {
        GptRequestDto request = new GptRequestDto(model, input);
        GptResponseDto response = restTemplate.postForObject(apiUrl, request, GptResponseDto.class);

        return PromptResponseDto.of(response.getChoices().get(0).getMessage().getContent());
    }
}
