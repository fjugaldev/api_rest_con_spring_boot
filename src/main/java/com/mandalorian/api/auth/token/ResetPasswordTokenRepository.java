package com.mandalorian.api.auth.token;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, String> {
    ResetPasswordToken findByToken(String Token);

    Optional<ResetPasswordToken> findById(String id);
}
