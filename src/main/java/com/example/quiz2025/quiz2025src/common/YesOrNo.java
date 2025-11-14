package com.example.quiz2025.quiz2025src.common;

public enum YesOrNo {
    Y("Yes"),
    N("No");

    private final String description;

    YesOrNo(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }

}