package com.example.quiz2025.quiz2025src.service.base;

import com.example.quiz2025.quiz2025src.dto.base.UserDto;
import com.example.quiz2025.quiz2025src.repository.base.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SearchUserService {

    private final UserRepository userRepository;

    /*유저 단건 조회*/
    @Transactional
    public UserDto searchUser(Long userId) {
        UserDto dto = userRepository.searchUser(userId);
        return dto;
    }
}
