package com.example.quiz2025.quiz2025src.service.room;

import com.example.quiz2025.quiz2025src.common.MessageCode;
import com.example.quiz2025.quiz2025src.domain.base.User;
import com.example.quiz2025.quiz2025src.domain.quiz.Question;
import com.example.quiz2025.quiz2025src.domain.room.Room;
import com.example.quiz2025.quiz2025src.domain.room.RoomParticipants;
import com.example.quiz2025.quiz2025src.dto.room.DeleteRoomParticipantDto;
import com.example.quiz2025.quiz2025src.exception.BusinessException;
import com.example.quiz2025.quiz2025src.repository.base.UserRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionRepository;
import com.example.quiz2025.quiz2025src.repository.room.RoomParticipantsRepository;
import com.example.quiz2025.quiz2025src.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeleteRoomParticipantService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final RoomParticipantsRepository roomParticipantsRepository;

    /* 방 참여 정보삭제 or 퇴장시간 기록 */
    public void deleteRoomParticipant(DeleteRoomParticipantDto dto) {

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new BusinessException(MessageCode.MSG_USER_NOT_FOUND, HttpStatus.NOT_FOUND));
        Room room = roomRepository.findById(dto.getRoomId()).orElseThrow(() -> new BusinessException(MessageCode.MSG_ROOM_NOT_FOUND, HttpStatus.NOT_FOUND));

        RoomParticipants participant = roomParticipantsRepository
                .findByRoomAndUser(room, user)
                .orElseThrow(() -> new BusinessException(
                        MessageCode.MSG_USER_NOT_FOUND,
                        HttpStatus.NOT_FOUND
                ));

        //대기중일 때 삭제
        if (room.getRoomStatus() == 0) {
            roomParticipantsRepository.delete(participant);
            room.decreaseCurrentPlayer(); //현재 참여자 수 -1
            return;
        }
        //그 외엔 퇴장시각 기록
        participant.setEndedDate(LocalDateTime.now().toString());

    }
}
