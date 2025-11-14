package com.example.quiz2025.quiz2025src.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 비즈니스 로직 예외 공통 처리
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException ex) {
        return ResponseEntity.status(ex.getStatus())
                .body(Map.of(
                        "status", ex.getStatus().value(),
                        "error", ex.getStatus().getReasonPhrase(),
                        "message", ex.getMessageCode().getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    // 그 외
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(Map.of(
                        "status", 500,
                        "error", "Internal Server Error",
                        "message", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}
