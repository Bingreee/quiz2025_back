package com.example.quiz2025.quiz2025src.dto.base;

import lombok.Getter;
import com.querydsl.core.annotations.QueryProjection;

@Getter
public class UserDto {
    private Long userId;
    private String userLoginId;
    private String userEmail;

    @QueryProjection
    public UserDto(Long userId, String userLoginId, String userEmail) {
        this.userId = userId;
        this.userLoginId = userLoginId;
        this.userEmail = userEmail;
    }
}
