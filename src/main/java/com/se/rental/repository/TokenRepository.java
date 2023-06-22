package com.se.rental.repository;


import com.se.rental.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
  boolean existsByRefreshToken(String refreshToken);

  void deleteByPreviousRefreshToken(String refreshToken);

  Token findByRefreshToken(String refreshToken);

  Token findByPreviousRefreshToken(String refreshToken);
}
