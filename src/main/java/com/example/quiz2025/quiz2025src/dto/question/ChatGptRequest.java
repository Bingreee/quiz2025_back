package com.example.quiz2025.quiz2025src.dto.question;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class ChatGptRequest {
    private String model;               // gpt 모델명
    private List<ChatGptMessage> messages; // 메시지 배열

    public ChatGptRequest(String model, List<ChatGptMessage> messages) {
        this.model = model;
        this.messages = messages;
    }
}

