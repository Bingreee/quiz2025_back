package com.example.quiz2025.quiz2025src.service.room;

import com.example.quiz2025.quiz2025src.common.MessageCode;
import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.domain.room.Room;
import com.example.quiz2025.quiz2025src.domain.room.RoomParticipants;
import com.example.quiz2025.quiz2025src.dto.room.CreateRoomParticipantDto;
import com.example.quiz2025.quiz2025src.exception.BusinessException;
import com.example.quiz2025.quiz2025src.repository.base.UserRepository;
import com.example.quiz2025.quiz2025src.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreateRoomParticipantService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;

    /*방 참여자 정보 생성*/
    public Long createRoomParticipant(CreateRoomParticipantDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new BusinessException(MessageCode.MSG_USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Room room = roomRepository.findById(dto.getRoomId()).orElseThrow(() -> new BusinessException(MessageCode.MSG_ROOM_NOT_FOUND, HttpStatus.NOT_FOUND));

        RoomParticipants roomParticipants = RoomParticipants.builder()
                .room(room)
                .user(user)
                .build();

        room.increaseCurrentPlayer(); //현재 참여자 수 +1

        return roomParticipants.getId();
    }
}
