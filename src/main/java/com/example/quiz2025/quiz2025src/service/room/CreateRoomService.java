package com.example.quiz2025.quiz2025src.service.room;

import com.example.quiz2025.quiz2025src.domain.category.Category;
import com.example.quiz2025.quiz2025src.domain.quiz.Question;
import com.example.quiz2025.quiz2025src.domain.room.Room;
import com.example.quiz2025.quiz2025src.repository.category.CategoryRepository;
import com.example.quiz2025.quiz2025src.repository.question.QuestionRepository;
import com.example.quiz2025.quiz2025src.repository.room.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CreateRoomService {

    private final RoomRepository roomRepository;
    private final QuestionRepository questionRepository;

    /*방 생성*/
    public Long createRoom(Long questionId) {

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("존재하지 않는 카테고리입니다."));
        Room room = roomRepository.save(
                Room.builder()
                        .category(question.getCategory())
                        .question(question)
                        .maxCapacity(8)
                        .currentPlayer(null)
                        .roomStatus(0)
                        .roundCount(25)
                        .build()

        );
        return room.getId();
    }
}
