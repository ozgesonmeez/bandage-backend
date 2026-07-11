package com.ozgesonmez.bandagebackend.dto.response;

public class RoleResponse {

    private Long id;
    private String code;
    private String name;

    public RoleResponse(Long id, String code, String name) {
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
}