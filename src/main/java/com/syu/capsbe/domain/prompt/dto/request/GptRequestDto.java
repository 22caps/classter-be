package com.syu.capsbe.domain.prompt.dto.request;

import com.syu.capsbe.domain.prompt.dto.Message;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class GptRequestDto {

    private final String model;
    private final List<Message> messages;

    public GptRequestDto(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "당신은 토익 강사입니다. 토익 질문에 대한 힌트를 한국어로 제공해주세요.")); // 프롬프트에게 역할을 부여
        this.messages.add(new Message("user", prompt));
    }
}
