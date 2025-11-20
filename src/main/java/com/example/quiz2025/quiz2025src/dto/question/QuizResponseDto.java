package com.example.quiz2025.quiz2025src.dto.question;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuizResponseDto {

    private List<QuizItem> items;

    @Getter @Setter
    public static class QuizItem {
        private String questionContent;
        private String questionAnswer;
        private Integer questionType; //객관식/주관식
        private Integer questionLevel;

        private List<ChoiceItem> choices;
    }

    @Getter @Setter
    public static class ChoiceItem {
        private Integer choiceNumber;
        private String choiceContent;
        private Boolean answerYn;
    }
}
