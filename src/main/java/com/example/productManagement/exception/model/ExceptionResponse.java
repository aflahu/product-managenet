package com.example.productManagement.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Builder
public record ExceptionResponse(
        Integer status,

        @JsonIgnore
        HttpStatus code,
        String message,
        Object errors
) implements Serializable {
}
