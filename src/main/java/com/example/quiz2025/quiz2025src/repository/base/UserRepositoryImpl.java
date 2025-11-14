package com.example.quiz2025.quiz2025src.repository.base;

import com.example.quiz2025.quiz2025src.dto.base.QUserDto;
import com.example.quiz2025.quiz2025src.dto.base.UserDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import static com.example.quiz2025.quiz2025src.domain.base.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public UserDto searchUser(Long userId) {
        return queryFactory
                .select(new QUserDto(
                        user.id,
                        user.userLoginId,
                        user.userEmail
                ))
                .from(user)
                .where(user.id.eq(userId))
                .fetchOne();
    }
}
