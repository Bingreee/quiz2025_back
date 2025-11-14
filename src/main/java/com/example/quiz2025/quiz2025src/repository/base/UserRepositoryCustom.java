package com.example.quiz2025.quiz2025src.repository.base;

import com.example.quiz2025.quiz2025src.dto.base.UserDto;

public interface UserRepositoryCustom {
    //유저 단건조회
    UserDto searchUser(Long userId);
}
