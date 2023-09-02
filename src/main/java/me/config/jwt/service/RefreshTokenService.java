package me.config.jwt.service;

import lombok.RequiredArgsConstructor;
import me.config.jwt.RefreshToken;
import me.config.jwt.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenService {
  private final RefreshTokenRepository refreshTokenRepository;

  public RefreshToken findByRefreshToken(String refreshToken){
    return refreshTokenRepository.findByRefreshToken(refreshToken)
        .orElseThrow(()->new IllegalArgumentException("Unexpected token"));
  }
}
