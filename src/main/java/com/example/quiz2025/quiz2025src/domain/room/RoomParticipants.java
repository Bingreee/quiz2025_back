package com.example.quiz2025.quiz2025src.domain.room;

import com.example.quiz2025.quiz2025src.common.BaseEntity;
import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.domain.category.Category;
import com.example.quiz2025.quiz2025src.domain.quiz.Question;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Table(name = "room_participants")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoomParticipants extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_participant_id")
    private Long id; // 방참여 ID

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "room_id")
    private Room room; // 방

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "user_id")
    private User user; // 사용자

    private String endedDate; // 퇴장 일시
}
