package com.example.productManagement.service;

import com.example.productManagement.model.User;
import com.example.productManagement.payload.request.LoginUserRequest;
import com.example.productManagement.payload.request.RegisterUserRequest;
import com.example.productManagement.repository.UserRepository;
import com.example.productManagement.payload.response.SignupResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;


    public SignupResponse signup(RegisterUserRequest input) {
        User user = User.builder()
                .id(UUID.randomUUID())
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();

        userRepository.save(user);
        return SignupResponse.builder()
                .userId(user.getId())
                .authorities(user.getAuthorities().toArray())
                .email(user.getEmail())
                .build();
    }

    public User authenticate(LoginUserRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}