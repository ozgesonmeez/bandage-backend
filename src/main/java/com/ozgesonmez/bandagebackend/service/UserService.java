package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.dto.request.LoginRequest;
import com.ozgesonmez.bandagebackend.dto.request.RegisterRequest;
import com.ozgesonmez.bandagebackend.dto.response.LoginResponse;
import com.ozgesonmez.bandagebackend.dto.response.UserResponse;
import com.ozgesonmez.bandagebackend.entity.AppUser;
import com.ozgesonmez.bandagebackend.entity.UserRole;
import com.ozgesonmez.bandagebackend.exception.EmailAlreadyExistsException;
import com.ozgesonmez.bandagebackend.exception.InvalidCredentialsException;
import com.ozgesonmez.bandagebackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        AppUser user = new AppUser();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(UserRole.CUSTOMER);

        AppUser savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getSurname(),
                savedUser.getEmail(),
                savedUser.getRole()
        );
    }

    public LoginResponse login(LoginRequest request) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(InvalidCredentialsException::new);

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new InvalidCredentialsException();
        }

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );

        String token = jwtService.generateToken(user);

        return new LoginResponse(token, userResponse);
    }
}