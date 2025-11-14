package com.example.quiz2025.quiz2025src.dto.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserForm {

    private String userLoginId;
    private String userPassword;
    private String confirmPassword;
    private String userEmail;
}
