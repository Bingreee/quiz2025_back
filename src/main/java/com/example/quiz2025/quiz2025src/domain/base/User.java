package com.example.quiz2025.quiz2025src.domain.base;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id; // 사용자ID

    private String userLoginId; //입력id

    private String userPassword; // 비밀번호

    private String userEmail; // 이메일

    @Builder
    public User(Long id, String userLoginId, String userPassword, String userEmail) {
        this.id = id;
        this.userLoginId = userLoginId;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }
}
