package com.se.rental.service;


import com.se.rental.entity.Token;

public interface TokenService {

    void save(Token token);

    boolean isRefreshTokenExist(String refreshToken);

    void deleteRefreshToken(String refreshToken);

    void updateRefreshToken(String refreshToken, String newRefreshToken);
}
