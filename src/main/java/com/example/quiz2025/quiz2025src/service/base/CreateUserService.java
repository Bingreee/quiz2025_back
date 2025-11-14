package com.example.quiz2025.quiz2025src.service.base;

import com.example.quiz2025.quiz2025src.common.MessageCode;
import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.dto.base.CreateUserForm;
import com.example.quiz2025.quiz2025src.exception.BusinessException;
import com.example.quiz2025.quiz2025src.repository.base.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@org.springframework.transaction.annotation.Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /*유저 정보 생성*/
    @Transactional
    public Long createUser(CreateUserForm createUserForm) {

        String loginId = createUserForm.getUserLoginId();
        String email = createUserForm.getUserEmail();
        String password = createUserForm.getUserPassword();
        String confirmPassword = createUserForm.getConfirmPassword();

        if (loginId == null || loginId.isBlank() ||
                email == null || email.isBlank() ||
                password == null || password.isBlank() ||
                confirmPassword == null || confirmPassword.isBlank()) {
            throw new BusinessException(MessageCode.MSG_EMPTY_REQUIRED_FIELD, HttpStatus.BAD_REQUEST);
        }

        // ID 중복 검사
        if (userRepository.existsByUserLoginId(loginId)) {
            throw new BusinessException(MessageCode.MSG_DUPLICATE_ID, HttpStatus.CONFLICT);
        }

        // ID 특수문자 검증 (영문+숫자만 허용)
        if (!loginId.matches("^[a-zA-Z0-9]+$")) {
            throw new BusinessException(MessageCode.MSG_INVALID_ID_FORMAT, HttpStatus.BAD_REQUEST);
        }

        //이메일 형식 검증 (간단한 RFC 형식)
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new BusinessException(MessageCode.MSG_INVALID_EMAIL_FORMAT, HttpStatus.BAD_REQUEST);
        }

        //이메일 중복 검사
        if (userRepository.existsByUserEmail(email)) {
            throw new BusinessException(MessageCode.MSG_DUPLICATE_EMAIL, HttpStatus.CONFLICT);
        }

        if (!password.equals(confirmPassword)) {
            throw new BusinessException(MessageCode.MSG_PASSWORD_MISMATCH, HttpStatus.BAD_REQUEST);
        }


        User user = userRepository.save(
                User.builder()
                        .userLoginId(createUserForm.getUserLoginId())
                        .userPassword(passwordEncoder.encode(password))
                        .userEmail(createUserForm.getUserEmail())
                        .build()
        );
        return user.getId();
    }
}
