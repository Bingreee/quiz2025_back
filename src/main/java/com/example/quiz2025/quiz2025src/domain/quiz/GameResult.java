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
@Table(name = "game_result")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GameResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id; // 게임 결과 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room; // 방

    private Integer totalPoint; // 총 포인트

    private Integer rank; // 순위
}
