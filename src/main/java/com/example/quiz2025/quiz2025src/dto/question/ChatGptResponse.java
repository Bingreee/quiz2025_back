package com.example.quiz2025.quiz2025src.dto.question;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class ChatGptResponse {
    private List<Choice> choices;

    @Getter
    @Setter
    public static class Choice {
        private Message message;

        @Getter @Setter
        public static class Message {
            private String role;
            private String content;
        }
    }
}
