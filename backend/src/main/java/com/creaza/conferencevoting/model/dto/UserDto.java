package com.creaza.conferencevoting.model.dto;

import javax.validation.constraints.NotBlank;

public class UserDto {

    @NotBlank
    private String email;

    public UserDto() {

    }

    public UserDto(@NotBlank String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
