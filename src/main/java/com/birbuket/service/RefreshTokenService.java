package com.birbuket.service;


import com.birbuket.entity.RefreshToken;
import com.birbuket.entity.UserEntity;
import com.birbuket.exception.InvalidTokenException;
import com.birbuket.repository.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private final RefreshTokenRepository refreshTokenRepository;

    // Refresh token yarat
    @Transactional
    public RefreshToken createRefreshToken(UserEntity user) {
        // Əvvəlki token-i sil
        refreshTokenRepository.deleteByUser(user);

        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiration(LocalDateTime.now().plusSeconds(refreshExpiration / 1000))
                .revoked(false)
                .build();

        return refreshTokenRepository.save(refreshToken);
    }

    // Token-i yoxla
    @Transactional
    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Refresh token not found!"));

        if (refreshToken.isRevoked()) {
            throw new InvalidTokenException("Refresh token already used!");
        }

        if (refreshToken.getExpiration().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new InvalidTokenException("Refresh token-in müddəti bitib!");
        }

        return refreshToken;
    }

    // Token-i ləğv et
    @Transactional
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new InvalidTokenException("Refresh token not found!"));

        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }
}