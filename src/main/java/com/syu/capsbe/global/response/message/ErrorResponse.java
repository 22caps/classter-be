package com.syu.capsbe.global.response.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final String errorCode;
    private final String message;

    public static ErrorResponse of(String errorCode, String message) {
        return new ErrorResponse(errorCode, message);
    }
}
