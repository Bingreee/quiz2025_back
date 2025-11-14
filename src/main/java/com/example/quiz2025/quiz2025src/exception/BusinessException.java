package com.example.quiz2025.quiz2025src.exception;

import com.example.quiz2025.quiz2025src.common.MessageCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    private final HttpStatus status; // 상태 코드
    private final MessageCode messageCode;

    public BusinessException(MessageCode message, HttpStatus status) {
        super(message.getMessage());
        this.status = status;
        this.messageCode = message;
    }
}
