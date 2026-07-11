package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.dto.request.LoginRequest;
import com.ozgesonmez.bandagebackend.dto.request.RegisterRequest;
import com.ozgesonmez.bandagebackend.dto.response.LoginResponse;
import com.ozgesonmez.bandagebackend.dto.response.UserResponse;
import com.ozgesonmez.bandagebackend.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import com.ozgesonmez.bandagebackend.exception.InvalidCredentialsException;
import com.ozgesonmez.bandagebackend.service.JwtService;
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(
            UserService userService,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @GetMapping("/verify")
    public LoginResponse verify(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        if (authorizationHeader == null
                || !authorizationHeader.startsWith("Bearer ")) {
            throw new InvalidCredentialsException();
        }

        String token = authorizationHeader.substring(7);

        if (!jwtService.isTokenValid(token)) {
            throw new InvalidCredentialsException();
        }

        String email = jwtService.extractEmail(token);

        return userService.verify(email);
    }

    @GetMapping("/me")
    public Authentication me(Authentication authentication) {
        return authentication;
    }
}