package io.elice.pokeranger.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceLogicException.class)
    public ResponseEntity<Object> handleServiceLogicException(ServiceLogicException ex) {
        ExceptionCode code = ex.getExceptionCode();
        ErrorResponse errorResponse = new ErrorResponse(code.getStatus(), ex.getMessage());
        return new ResponseEntity<>(errorResponse, code.getStatus());
    }

    // ErrorResponse 클래스 (내부 클래스로 정의)
    @Getter
    private static class ErrorResponse {
        // Getters
        private final HttpStatus status;
        private final String message;

        public ErrorResponse(HttpStatus status, String message) {
            this.status = status;
            this.message = message;
        }

    }
}