package com.example.productManagement.controller;

import com.example.productManagement.model.User;
import com.example.productManagement.payload.response.MeResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@SecurityRequirement(name = "Authorization")
public class UserController {

    @GetMapping("/me")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<MeResponse> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(MeResponse.builder()
                .authorities(currentUser.getAuthorities().toArray())
                .email(currentUser.getEmail())
                .userId(currentUser.getId())
                .build());
    }
}
