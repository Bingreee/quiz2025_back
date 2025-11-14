package com.example.quiz2025.quiz2025src.domain.quiz;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.domain.room.Room;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "round_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoundResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "round_result_id")
    private Long id; // 라운드 결과 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room; // 방

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question; // 질문

    private Integer getPoint; // 획득 포인트

    private Integer rank; // 정답 순위
}
