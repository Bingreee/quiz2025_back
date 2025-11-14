package com.example.quiz2025.quiz2025src.api.base;

import com.example.quiz2025.quiz2025src.common.ResultCode;
import com.example.quiz2025.quiz2025src.common.Result;
import com.example.quiz2025.quiz2025src.dto.base.CreateUserForm;
import com.example.quiz2025.quiz2025src.service.base.CreateUserService;
import com.example.quiz2025.quiz2025src.service.base.SearchUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final CreateUserService createUserService;
    private final SearchUserService searchUserService;

    /*유저 생성*/
    @PostMapping("/user")
    public Result createUser(@RequestBody @Validated CreateUserForm createUserForm) {
        return new Result<>(ResultCode.SUCCESS, createUserService.createUser(createUserForm), "result_success_save", "200");
    }

    /*유저 단건 조회*/
    @GetMapping("/user/{userID}")
    public Result searchUser(@PathVariable Long userID) {
        return new Result<>(ResultCode.SUCCESS, searchUserService.searchUser(userID), "result_success_search", "200");
    }
}
