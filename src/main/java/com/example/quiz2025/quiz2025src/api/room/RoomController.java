package com.example.quiz2025.quiz2025src.api.room;

import com.example.quiz2025.quiz2025src.common.Result;
import com.example.quiz2025.quiz2025src.common.ResultCode;
import com.example.quiz2025.quiz2025src.dto.room.CreateRoomParticipantDto;
import com.example.quiz2025.quiz2025src.dto.room.DeleteRoomParticipantDto;
import com.example.quiz2025.quiz2025src.dto.room.RoomStatusDto;
import com.example.quiz2025.quiz2025src.service.room.CreateRoomParticipantService;
import com.example.quiz2025.quiz2025src.service.room.CreateRoomService;
import com.example.quiz2025.quiz2025src.service.room.DeleteRoomParticipantService;
import com.example.quiz2025.quiz2025src.service.room.UpdateRoomStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RoomController {
    private final CreateRoomService createRoomService;
    private final CreateRoomParticipantService createRoomParticipantService;
    private final UpdateRoomStatusService updateRoomStatusService;
    private final DeleteRoomParticipantService deleteRoomParticipantService;

    /* 방 생성 */
    @PostMapping("/room")
    public Result createRoom(@RequestBody Long questionId) {
        return new Result<>(ResultCode.SUCCESS, createRoomService.createRoom(questionId), "result_success_save", "200");
    }

    /* 방 상태 변경*/
    @PutMapping("/room/status")
    public Result updateRoomStatus(@RequestBody RoomStatusDto dto) {
        updateRoomStatusService.updateRoomStatus(dto.getRoomId(), dto.getRoomStatus());
        return new Result<>(ResultCode.SUCCESS,"result_success_save", "200");
    }

    /* 방 참여자 생성 */
    @PostMapping("/room/participant")
    public Result createRoomParticipant(@RequestBody CreateRoomParticipantDto dto) {
        return new Result<>(ResultCode.SUCCESS, createRoomParticipantService.createRoomParticipant(dto), "result_success_save", "200");
    }

    /* 방 참여자 삭제*/
    @DeleteMapping("/room/user")
    public Result deleteRoomParticipant(@RequestBody DeleteRoomParticipantDto dto) {
        deleteRoomParticipantService.deleteRoomParticipant(dto);
        return new Result<>(ResultCode.SUCCESS,"result_success_save", "200");
    }
}
