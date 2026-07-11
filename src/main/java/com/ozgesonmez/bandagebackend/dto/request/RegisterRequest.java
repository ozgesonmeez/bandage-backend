package com.ozgesonmez.bandagebackend.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegisterRequest {

    private String name;
    private String surname;
    private String email;
    private String password;

    @JsonProperty("role_id")
    private Long roleId;

    private StoreRequest store;

    public RegisterRequest() {
    }

    public RegisterRequest(
            String name,
            String surname,
            String email,
            String password,
            Long roleId,
            StoreRequest store
    ) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.roleId = roleId;
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public StoreRequest getStore() {
        return store;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setStore(StoreRequest store) {
        this.store = store;
    }
}