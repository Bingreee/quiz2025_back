package com.example.quiz2025.quiz2025src.repository.base;

import com.example.quiz2025.quiz2025src.domain.base.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    boolean existsByUserEmail(String email);
    boolean existsByUserLoginId(String loginId);
    Optional<User> findByUserLoginId(String loginId);

}
