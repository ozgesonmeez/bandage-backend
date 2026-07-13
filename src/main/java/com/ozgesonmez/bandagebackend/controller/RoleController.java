package com.ozgesonmez.bandagebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @GetMapping("/roles")
    public List<RoleResponse> getRoles() {
        return List.of(
                new RoleResponse(
                        3L,
                        "customer",
                        "Customer"
                ),
                new RoleResponse(
                        2L,
                        "store",
                        "Store"
                )
        );
    }

    public record RoleResponse(
            Long id,
            String code,
            String name
    ) {
    }
}