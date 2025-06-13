package com.example.borolo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.borolo.exception.FieldErrorDto;


@RestControllerAdvice(basePackages = "com.example.borolo.controller")
public class GlobalExceptionHandler { // 애플리케이션 전역에서 발생하는 예외를 한 곳에서 처리하는 공통 예외 처리 코드

    // DTO에서 @Valid 실패한 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> new FieldErrorDto(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ErrorResponse(400, errors));
    }

    // Validator에서 Errors 사용했을 때 (예: 커스텀 Validator)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException ex) {
        List<FieldErrorDto> errors = ex.getBindingResult().getFieldErrors().stream()
            .map(error -> new FieldErrorDto(error.getField(), error.getDefaultMessage()))
            .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(new ErrorResponse(400, errors));
    }

    // 그 외 IllegalArgumentException 처리 등 추가 가능
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
