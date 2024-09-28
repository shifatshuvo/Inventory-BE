package com.example.inventory_management.repository;

import com.example.inventory_management.model.auth.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<AuthToken, Long> {
    Optional<AuthToken> findByTokenStr(String token);
    void deleteByUserId(Long userId);
}
