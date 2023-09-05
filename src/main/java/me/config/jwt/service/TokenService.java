package me.config.jwt.service;

import java.time.Duration;
import lombok.RequiredArgsConstructor;
import me.config.jwt.TokenProvider;
import me.user.domain.User;
import me.user.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class TokenService {

  private final TokenProvider tokenProvider;
  private final RefreshTokenService refreshTokenService;
  private final UserService userService;

  /***
   * 리프레시 토큰으로 유효성 검사를 진행하고 유효한 토큰일 경우 리프레시 토큰으로 사용자 ID를 찾는다.
   * 마지막으로 사용자 ID로 사용자를 찾은 후에 토큰 제공자의 generateToken() 으로 새로운 액세스토큰을 찾는다.
   * @param refreshToken
   * @return
   */
  public String createNewAccessToken(String refreshToken) {
    // 토큰 유효성 검사에 실패하면 예외 발생
    if (!tokenProvider.validToken(refreshToken)) {
      throw new IllegalArgumentException("Unexpected token");
    }
    Long userId = refreshTokenService.findByRefreshToken(refreshToken).getUserId();
    User user = userService.findById(userId);
    return tokenProvider.generateToken(user, Duration.ofHours(2));
  }
}