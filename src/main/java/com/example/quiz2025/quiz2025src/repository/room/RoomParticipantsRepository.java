package com.example.quiz2025.quiz2025src.repository.room;

import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.domain.room.Room;
import com.example.quiz2025.quiz2025src.domain.room.RoomParticipants;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomParticipantsRepository extends JpaRepository<RoomParticipants, Long> {

    Optional<RoomParticipants> findByRoomAndUser(Room room, User user);
}
