package com.ssafy.fitnect.util;

import org.springframework.http.HttpStatus;

public record ApiResponse(
        ApiStatus status,
        Integer code,
        String message,
        Object data
) {
    public static ApiResponse success(HttpStatus httpStatus, Object data) {
        return new ApiResponse(ApiStatus.SUCCESS, httpStatus.value(), null, data);
    }

    public static ApiResponse error(HttpStatus httpStatus, String message) {
        return new ApiResponse(ApiStatus.ERROR, httpStatus.value(), message, null);
    }
}