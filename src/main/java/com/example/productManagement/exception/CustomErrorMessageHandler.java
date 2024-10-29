package com.example.productManagement.exception;

import com.example.productManagement.exception.model.ExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class CustomErrorMessageHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        log.info("ex class: {}", ex.getClass());
        if(ex instanceof MethodArgumentNotValidException exception){
            List<String> errorResponse = new ArrayList<>();
            if(!ObjectUtils.isEmpty(exception.getDetailMessageArguments())){
                for(Object object: exception.getDetailMessageArguments()){
                    if (!ObjectUtils.isEmpty(object)) {
                        errorResponse.add(String.valueOf(object));
                    }
                }
            }

            return new ResponseEntity<>(ExceptionResponse.builder()
                    .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .code(HttpStatus.INTERNAL_SERVER_ERROR)
                    .message("MethodArgumentNotValidException Errors")
                    .errors(errorResponse)
                    .build(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(ExceptionResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .errors(null)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
