package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.dto.request.LoginRequest;
import com.ozgesonmez.bandagebackend.dto.request.RegisterRequest;
import com.ozgesonmez.bandagebackend.dto.response.LoginResponse;
import com.ozgesonmez.bandagebackend.dto.response.UserResponse;
import com.ozgesonmez.bandagebackend.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }
    @GetMapping("/me")
    public Authentication me(Authentication authentication) {
        return authentication;
    }
}