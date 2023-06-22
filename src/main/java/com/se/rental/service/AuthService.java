package com.se.rental.service;


import com.se.rental.entity.dto.request.LoginRequest;
import com.se.rental.entity.dto.request.RefreshTokenRequest;
import com.se.rental.entity.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest loginRequest);
    LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
