package com.example.quiz2025.redis.repository;

import com.example.quiz2025.redis.dto.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
