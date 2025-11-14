package com.example.quiz2025.quiz2025src.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {

    MSG_DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
    MSG_INVALID_EMAIL_FORMAT("올바른 이메일 형식이 아닙니다."),
    MSG_PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
    MSG_INVALID_ID_FORMAT("아이디는 영문과 숫자 조합만 가능합니다."),
    MSG_DUPLICATE_ID("이미 사용 중인 아이디입니다."),
    MSG_INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),
    MSG_USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    MSG_EMPTY_REQUIRED_FIELD("필수 입력 항목이 누락되었습니다.");

    private final String message;
}
