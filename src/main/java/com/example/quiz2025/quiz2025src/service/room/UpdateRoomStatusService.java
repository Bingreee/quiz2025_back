package com.example.quiz2025.quiz2025src.service.room;

import com.example.quiz2025.quiz2025src.common.MessageCode;
import com.example.quiz2025.quiz2025src.domain.room.Room;
import com.example.quiz2025.quiz2025src.exception.BusinessException;
import com.example.quiz2025.quiz2025src.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UpdateRoomStatusService {

    private final RoomRepository roomRepository;

    public void updateRoomStatus(Long roomId, Integer newStatus) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new BusinessException(MessageCode.MSG_ROOM_NOT_FOUND, HttpStatus.NOT_FOUND));

        validateStatusChange(room, newStatus);

        // 상태 변경
        room.changeStatus(newStatus);

        // 종료 상태면 종료일 기록
        if (newStatus == 2) {
            room.setEndedDate(LocalDateTime.now().toString());
        }
    }

    private void validateStatusChange(Room room, Integer newStatus) {

        if (newStatus < 0 || newStatus > 2) {
            throw new BusinessException(MessageCode.MSG_INVALID_STATUS, HttpStatus.BAD_REQUEST
            );
        }

        // 종료된 방은 변경 불가
        if (room.getRoomStatus() == 2) {
            throw new BusinessException(MessageCode.MSG_ROOM_CLOSED, HttpStatus.BAD_REQUEST
            );
        }

        // 같은 상태로 변경하려는 경우
        if (room.getRoomStatus().equals(newStatus)) {
            throw new BusinessException(MessageCode.MSG_DUPLICATE_ACTION, HttpStatus.BAD_REQUEST
            );
        }
    }
}
