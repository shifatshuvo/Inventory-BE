package com.example.inventory_management.dto;

import com.example.inventory_management.model.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInResponseDto {
    private User user;
    private String tokenStr;
}
