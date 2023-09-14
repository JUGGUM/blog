package me.user.service;

import lombok.RequiredArgsConstructor;
import me.user.domain.User;
import me.user.dto.AddUserRequest;
import me.user.repository.UserRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

  private final UserRepository userRepository;
//  private final BCryptPasswordEncoder bCryptPasswordEncoder;

//  public Long save(AddUserRequest dto) {
//    return userRepository.save(User.builder()
//        .email(dto.getEmail())
//        .password(bCryptPasswordEncoder.encode(dto.getPassword()))
//        .build()).getId();
//  }

  public Long save(AddUserRequest dto) {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    return userRepository.save(User.builder()
        .email(dto.getEmail())
        .password(encoder.encode(dto.getPassword()))
        .build()).getId();
  }

  public User findByEmail(String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new IllegalArgumentException("Unexpected user"));
  }


  public User findById(Long userId) {
    return userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Unexpected User"));
  }
}
