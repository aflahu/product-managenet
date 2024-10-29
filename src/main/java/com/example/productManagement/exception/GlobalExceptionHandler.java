package com.example.productManagement.exception;


import com.example.productManagement.exception.model.BaseException;
import com.example.productManagement.exception.model.ExceptionResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleApplicationException(final BaseException exception, final HttpServletRequest request) {
        ExceptionResponse response = ExceptionResponse.builder()
                .status(exception.getStatus())
                .message(exception.getMessage())
                .code(exception.getCode())
                .errors(exception.getErrors())
                .build();
        return new ResponseEntity<>(response, exception.getCode());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .errors(null)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleSqlError(SQLException exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(exception.getMessage())
                .errors(null)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ValidationException.class})
    public ResponseEntity<Object> handleException(ValidationException validationException) {
        if (!(validationException instanceof ConstraintViolationException)) {
            String exceptionMessage = validationException.getMessage();
            log.error(exceptionMessage, validationException);
            return new ResponseEntity<>(ExceptionResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .code(HttpStatus.BAD_REQUEST)
                    .message(validationException.getMessage())
                    .errors(null)
                    .build(), HttpStatus.BAD_REQUEST);
        }

        String violations = extractViolationsFromException((ConstraintViolationException) validationException);
        log.error(violations, validationException);
        return new ResponseEntity<>(ExceptionResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(HttpStatus.BAD_REQUEST)
                .message(violations)
                .errors(null)
                .build(), HttpStatus.BAD_REQUEST);
    }

    private String extractViolationsFromException(ConstraintViolationException validationException) {
        return validationException.getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("--"));
    }

}
