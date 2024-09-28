package com.example.inventory_management.controller;

import com.example.inventory_management.dto.SignInDto;
import com.example.inventory_management.dto.SignInResponseDto;
import com.example.inventory_management.model.User;
import com.example.inventory_management.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    // sign up a user
//    @PostMapping("/create")
//    private ResponseEntity<String> saveUser(@RequestBody User user) {
//        String response = authService.storedUser(user);
//        if (response.equals("Email or password cannot be empty!") || response.equals("Email already in use!")) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//        } else {
//            return ResponseEntity.ok(response);
//        }
//    }

    @PostMapping("/sign-up")
    private ResponseEntity<Map<String, String>> saveUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        String result = authService.storedUser(user);

        if (result.equals("Email or password cannot be empty!") || result.equals("Email already in use!")) {
            response.put("message", result);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        } else {
            response.put("message", result);
            return ResponseEntity.ok(response);
        }
    }



    // sign in a user
    @PostMapping("/sign-in")
    public SignInResponseDto doSignIn(@RequestBody @Valid SignInDto dto) {
        return authService.signIn(dto);
    }
}
