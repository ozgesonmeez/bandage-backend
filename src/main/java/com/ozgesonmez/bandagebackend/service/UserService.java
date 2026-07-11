package com.ozgesonmez.bandagebackend.service;

import com.ozgesonmez.bandagebackend.dto.request.LoginRequest;
import com.ozgesonmez.bandagebackend.dto.request.RegisterRequest;
import com.ozgesonmez.bandagebackend.dto.request.StoreRequest;
import com.ozgesonmez.bandagebackend.dto.response.LoginResponse;
import com.ozgesonmez.bandagebackend.dto.response.UserResponse;
import com.ozgesonmez.bandagebackend.entity.AppUser;
import com.ozgesonmez.bandagebackend.entity.Store;
import com.ozgesonmez.bandagebackend.entity.UserRole;
import com.ozgesonmez.bandagebackend.exception.EmailAlreadyExistsException;
import com.ozgesonmez.bandagebackend.exception.InvalidCredentialsException;
import com.ozgesonmez.bandagebackend.repository.StoreRepository;
import com.ozgesonmez.bandagebackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserService(
            UserRepository userRepository,
            StoreRepository storeRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public UserResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        UserRole selectedRole = UserRole.fromId(request.getRoleId());

        if (selectedRole == UserRole.STORE && request.getStore() == null) {
            throw new IllegalArgumentException(
                    "Store information is required when the selected role is STORE."
            );
        }

        AppUser user = new AppUser();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(selectedRole);

        AppUser savedUser = userRepository.save(user);

        if (selectedRole == UserRole.STORE) {
            StoreRequest storeRequest = request.getStore();

            Store store = new Store();
            store.setName(storeRequest.getName());
            store.setPhone(storeRequest.getPhone());
            store.setTaxNo(storeRequest.getTax_no());
            store.setBankAccount(storeRequest.getBank_account());
            store.setUser(savedUser);

            storeRepository.save(store);
        }

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
    public LoginResponse verify(String email) {
        AppUser user = userRepository.findByEmail(email)
                .orElseThrow(InvalidCredentialsException::new);

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getEmail(),
                user.getRole()
        );

        String renewedToken = jwtService.generateToken(user);

        return new LoginResponse(renewedToken, userResponse);
    }
}