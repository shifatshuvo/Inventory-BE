package com.example.inventory_management.controller;

import com.example.inventory_management.model.User;
import com.example.inventory_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;

//    // Get all users
//    @GetMapping
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

//    // Get a single user by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id) {
//        return userService.getUserById(id)
//            .map(ResponseEntity::ok)
//            .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Get user by email
//    @GetMapping("/email/{email}")
//    public User getUserByEmail(@PathVariable String email) {
//        return userService.getUserByEmail(email);
//    }

//    // Create a new user
//    @PostMapping
//    public User createUser(@RequestBody User user) {
//        return userService.createUser(user);
//    }
//
//    // Update user details by ID
//    @PutMapping("/{id}")
//    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
//        return ResponseEntity.ok(userService.updateUser(id, userDetails));
//    }
//
//    // Delete a user by ID
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }

    // Get total number of users
    @GetMapping("/count")
    public long getTotalUsers() {
        return userService.getTotalUsers();
    }
}
