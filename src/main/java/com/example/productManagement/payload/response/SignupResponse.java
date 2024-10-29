package com.example.productManagement.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@RequiredArgsConstructor
public class SignupResponse {
    private UUID userId;
    private String email;
    private Object[] authorities;
}
