package com.example.quiz2025.quiz2025src.dto.question;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class QuestionChoiceDto {

    private int choiceId;
    private int questionDetailId;
    private String choiceContent;
    private int choiceNumber;
    private String answerYn;
}
