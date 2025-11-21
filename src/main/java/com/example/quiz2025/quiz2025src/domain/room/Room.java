package com.example.quiz2025.quiz2025src.domain.room;

import com.example.quiz2025.quiz2025src.domain.category.Category;
import com.example.quiz2025.quiz2025src.domain.quiz.Question;
import jakarta.persistence.*;
import com.example.quiz2025.quiz2025src.common.BaseEntity;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class Room extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id; // 방 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id")
    private Category category; // 카테고리

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "question_id")
    private Question question; // 대표 질문

    private Integer maxCapacity; // 정원

    private Integer currentPlayer; // 현재 참여자 수

    private Integer roomStatus; // 방 상태 (0:대기,1:진행,2:종료)

    private Integer roundCount; // 라운드 수

    @Setter
    private String endedDate; // 종료 일시

    @Builder
    public Room(Long id, Category category, Question question, Integer maxCapacity, Integer currentPlayer, Integer roomStatus, Integer roundCount, String endedDate) {
        this.id = id;
        this.category = category;
        this.question = question;
        this.maxCapacity = maxCapacity;
        this.currentPlayer = currentPlayer;
        this.roomStatus = roomStatus;
        this.roundCount = roundCount;
        this.endedDate = endedDate;
    }

    public void changeStatus(Integer status) {
        this.roomStatus = status;
    }

    public void increaseCurrentPlayer() {
        this.currentPlayer = (this.currentPlayer == null ? 1 : this.currentPlayer + 1);
    }

    public void decreaseCurrentPlayer() {
        if (this.currentPlayer != null && this.currentPlayer > 0) {
            this.currentPlayer -= 1;
        }
    }


}
