package com.example.quiz2025.quiz2025src.dto.question;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatGptMessage {
    private String role;
    private String content;

    public ChatGptMessage(String role, String content) {
        this.role = role;
        this.content = content;
    }
}
