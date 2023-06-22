package com.se.rental.service.impl;


import com.se.rental.repository.TokenRepository;
import com.se.rental.service.TokenService;
import com.se.rental.entity.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

  private TokenRepository tokenRepository;

  @Autowired
  public void setTokenRepository(TokenRepository tokenRepository) {
    this.tokenRepository = tokenRepository;
  }

  @Override
  public void save(Token token) {
    tokenRepository.save(token);
  }

  @Override
  public boolean isRefreshTokenExist(String refreshToken) {
    return tokenRepository.existsByRefreshToken(refreshToken);
  }

  @Override
  public void deleteRefreshToken(String refreshToken) {
    Token token = tokenRepository.findByPreviousRefreshToken(refreshToken);
    if (token != null) {
      tokenRepository.delete(token);
    }
  }

  @Override
  public void updateRefreshToken(String refreshToken, String newRefreshToken) {
    Token token = tokenRepository.findByRefreshToken(refreshToken);
    token.setPreviousRefreshToken(token.getRefreshToken());
    token.setRefreshToken(newRefreshToken);
    tokenRepository.save(token);
  }
}
