package com.example.quiz2025.quiz2025src.common;

import lombok.Getter;
import java.util.List;

@Getter
public class Result<T> {
    private ResultCode result;
    private T data;
    private String message;
    private String code;
    private List<Error> errors;

    public Result(ResultCode result, T data) {
        this.result = result;
        this.data = data;
    }

    public Result(ResultCode result, String message) {
        this.result = result;
        this.message = message;
    }

    public Result(ResultCode result, String message, String code) {
        this.result = result;
        this.message = message;
        this.code = code;
    }

    public Result(ResultCode result, T data, String message, String code) {
        this.result = result;
        this.data = data;
        this.message = message;
        this.code = code;
    }


}
