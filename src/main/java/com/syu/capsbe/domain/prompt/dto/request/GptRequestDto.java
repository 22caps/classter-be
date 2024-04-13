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
        this.messages.add(new Message("user", prompt));
    }
}
