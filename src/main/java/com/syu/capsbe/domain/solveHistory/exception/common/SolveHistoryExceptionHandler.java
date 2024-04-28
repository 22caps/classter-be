package com.syu.capsbe.domain.solveHistory.exception.common;

import com.syu.capsbe.domain.solveHistory.exception.SolveHistoryExistsException;
import com.syu.capsbe.global.response.message.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class SolveHistoryExceptionHandler {

    @ExceptionHandler(SolveHistoryExistsException.class)
    public ResponseEntity<ErrorResponse> handleSolveHistoryExistsException(SolveHistoryExistsException e) {
        log.error("SolveHistoryExistsException: {}", e.getErrorMessage());
        return ResponseEntity.badRequest()
                .body(ErrorResponse.of(e.getErrorCode(), e.getErrorMessage()));
    }
}
