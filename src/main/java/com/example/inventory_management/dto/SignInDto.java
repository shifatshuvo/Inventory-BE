package com.example.inventory_management.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignInDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
