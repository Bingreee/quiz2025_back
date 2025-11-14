package com.example.quiz2025.redis.dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 86400) // TTL: 24시간
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class RefreshToken {

    @Id
    private String userId; // Redis key (User ID 기준)

    private String token;

    public void updateToken(String newToken) {
        this.token = newToken;
    }

}
