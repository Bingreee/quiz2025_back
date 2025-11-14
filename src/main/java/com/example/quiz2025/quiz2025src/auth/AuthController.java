package com.example.quiz2025.quiz2025src.auth;

import com.example.quiz2025.redis.dto.LoginForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    /** 로그인 (Access + Refresh 발급) */
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginForm loginForm) {
        Map<String, String> tokens = authService.login(loginForm.getUserLoginId(), loginForm.getUserPassword());
        return ResponseEntity.ok(tokens);
    }

    /**Access Token 재발급 (Refresh Token 검증 후 새 Access 발급) */
    @PostMapping("/reissue")
    public ResponseEntity<Map<String, String>> reissue(@RequestBody Map<String, String> tokenRequest) {
        String refreshToken = tokenRequest.get("refreshToken");
        Map<String, String> newTokens = authService.reissue(refreshToken);
        return ResponseEntity.ok(newTokens);
    }

    /** 로그아웃 (Redis에서 Refresh Token 삭제) */
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String refreshToken) {
        authService.logout(refreshToken.replace("Bearer ", ""));
        return ResponseEntity.ok("로그아웃 성공");
    }
}
