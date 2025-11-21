package com.example.quiz2025.quiz2025src.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageCode {

    // ====== 로그인/회원가입 ======
    MSG_DUPLICATE_EMAIL("이미 사용 중인 이메일입니다."),
    MSG_INVALID_EMAIL_FORMAT("올바른 이메일 형식이 아닙니다."),
    MSG_PASSWORD_MISMATCH("비밀번호가 일치하지 않습니다."),
    MSG_INVALID_ID_FORMAT("아이디는 영문과 숫자 조합만 가능합니다."),
    MSG_DUPLICATE_ID("이미 사용 중인 아이디입니다."),
    MSG_INVALID_PASSWORD("비밀번호가 일치하지 않습니다."),
    MSG_USER_NOT_FOUND("사용자를 찾을 수 없습니다."),

    // ====== 비즈니스 로직 ======
    MSG_EMPTY_REQUIRED_FIELD("필수 입력 항목이 누락되었습니다."),
    MSG_INVALID_STATUS("유효하지 않은 상태 값입니다."),
    MSG_ACTION_NOT_ALLOWED("해당 작업을 수행할 수 없습니다."),
    MSG_DUPLICATE_ACTION("이미 처리된 요청입니다."),
    MSG_MAX_CAPACITY_REACHED("정원을 초과했습니다."),
    MSG_ROOM_CLOSED("이미 종료된 방입니다."),
    MSG_ROOM_NOT_FOUND("방을 찾을 수 없습니다."),
    MSG_QUIZ_ALREADY_STARTED("퀴즈가 이미 시작되었습니다."),
    MSG_QUIZ_NOT_STARTED("퀴즈가 아직 시작되지 않았습니다."),
    MSG_ROUND_NOT_ALLOWED("현재 라운드에서는 허용되지 않는 작업입니다.");

    private final String message;
}
