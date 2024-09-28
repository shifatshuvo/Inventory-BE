package com.example.inventory_management.repository;

import com.example.inventory_management.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailIgnoreCaseAndPassword(String email, String password);
    User findByEmail(String email);
    User getUserById(Long id);
//    Optional<User> findByEmail(String email);
}
