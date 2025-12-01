package com.example.quiz2025.quiz2025src.dto.question;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class QuestionDetailDto {

    private int questionId;
    private int questionDetailId;
    private String questionContent;
    private String questionAnswer;
    private int questionType;

    private List<QuestionChoiceDto> choices;

}
