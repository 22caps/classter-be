package com.syu.capsbe.domain.member.exception.common;

import com.syu.capsbe.domain.member.exception.InvalidGoalScoreException;
import com.syu.capsbe.domain.member.exception.MemberEmailInvalidException;
import com.syu.capsbe.domain.member.exception.MemberExistsException;
import com.syu.capsbe.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MemberExceptionHandler {

    @ExceptionHandler(MemberEmailInvalidException.class)
    public ResponseEntity<ErrorResponse> catchNonExistentEmail(MemberEmailInvalidException e) {
        log.warn(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(MemberExistsException.class)
    public ResponseEntity<ErrorResponse> catchMemberExists(MemberExistsException e) {
        log.warn(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }

    @ExceptionHandler(InvalidGoalScoreException.class)
    public ResponseEntity<ErrorResponse> catchInvalidGoalScore(InvalidGoalScoreException e) {
        log.warn(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
