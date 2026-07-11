package com.ozgesonmez.bandagebackend.controller;

import com.ozgesonmez.bandagebackend.dto.response.RoleResponse;
import com.ozgesonmez.bandagebackend.entity.UserRole;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin
public class RoleController {

    @GetMapping("/roles")
    public List<RoleResponse> getRoles() {
        return Arrays.stream(UserRole.values())
                .map(role -> new RoleResponse(
                        role.getId(),
                        role.getCode(),
                        role.getName()
                ))
                .toList();
    }
}