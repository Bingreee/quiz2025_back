package com.example.quiz2025.quiz2025src.auth;

import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.repository.base.UserRepository;
import com.example.quiz2025.quiz2025src.service.base.CreateUserService;
import com.example.quiz2025.redis.dto.RefreshToken;
import com.example.quiz2025.redis.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    // 로그인 시 Redis에 RefreshToken 저장
    public Map<String, String> login(String userLoginId, String userPassword) {
        User user = userRepository.findByUserLoginId(userLoginId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 아이디입니다."));

        if (!passwordEncoder.matches(userPassword, user.getUserPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getUserLoginId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId());

        // Redis에 저장 (기존 데이터 덮어쓰기)
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .userId(String.valueOf(user.getId()))
                        .token(refreshToken)
                        .build()
        );

        return Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken
        );
    }

    // Refresh Token 재발급
    public Map<String, String> reissue(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(refreshToken);

        RefreshToken storedToken = refreshTokenRepository.findById(String.valueOf(userId))
                .orElseThrow(() -> new RuntimeException("Refresh Token이 존재하지 않습니다."));

        if (!storedToken.getToken().equals(refreshToken)) {
            throw new RuntimeException("토큰이 일치하지 않습니다.");
        }

        String newAccessToken = jwtTokenProvider.generateAccessToken(userId, "user");
        return Map.of(
                "accessToken", newAccessToken,
                "refreshToken", refreshToken
        );
    }

    public void logout(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }

        Long userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
        try {
            // deleteById() 호출 시 null-safe 처리
            refreshTokenRepository.findById(String.valueOf(userId))
                    .ifPresent(token -> refreshTokenRepository.deleteById(String.valueOf(userId)));
        } catch (Exception e) {
            System.err.println("Redis 삭제 중 예외 발생: " + e.getMessage());
        }
    }

}
