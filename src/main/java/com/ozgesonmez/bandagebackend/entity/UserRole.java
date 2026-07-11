package com.ozgesonmez.bandagebackend.entity;

public enum UserRole {

    CUSTOMER(1L, "customer", "Customer"),
    STORE(2L, "store", "Store"),
    ADMIN(3L, "admin", "Admin");

    private final Long id;
    private final String code;
    private final String name;

    UserRole(Long id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static UserRole fromId(Long id) {
        for (UserRole role : values()) {
            if (role.getId().equals(id)) {
                return role;
            }
        }

        throw new IllegalArgumentException("Invalid role id: " + id);
    }
}