package com.se.rental.service.impl;


import com.se.rental.service.AuthService;
import com.se.rental.service.TokenService;
import com.se.rental.service.UserService;
import com.se.rental.entity.Token;
import com.se.rental.entity.dto.request.LoginRequest;
import com.se.rental.entity.dto.request.RefreshTokenRequest;
import com.se.rental.entity.dto.response.LoginResponse;
import com.se.rental.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final TokenService tokenService;


@Autowired
UserService userService;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        try {
            var result = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            log.info("Bad Credentials");
            var loginResponse = new LoginResponse("", "","wrong email or password.");
            return loginResponse;
        }

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(loginRequest.getEmail());

        final String accessToken = jwtUtil.generateToken(userDetails,userService.findByEmail(userDetails.getUsername()));
        final String refreshToken = jwtUtil.generateRefreshToken(loginRequest.getEmail());
        var loginResponse = new LoginResponse(accessToken, refreshToken,"Success!");



        Token token = new Token(refreshToken, refreshToken);
        token.setRefreshToken(refreshToken);
        tokenService.save(token);

        return loginResponse;
    }

    @Override
    public LoginResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        boolean isRefreshTokenValid = jwtUtil.validateToken(refreshTokenRequest.getRefreshToken());
        if (isRefreshTokenValid) {
            boolean isAccessTokenExpired = jwtUtil.isTokenExpired(refreshTokenRequest.getAccessToken());
            if (isAccessTokenExpired) {
                // if refreshToken doesn't exist in DB,
                // then the passed token should be the previous_refreshToken
                if (!tokenService.isRefreshTokenExist(refreshTokenRequest.getRefreshToken())) {
                    // deleting token object based on previous_refreshToken
                    // after this step, re-login is required
                    tokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
                    throw new RuntimeException("Invalid Refresh Token");
                }
                // generate new refresh token and update it into token table
                String newRefreshToken =
                        jwtUtil.generateNewRefreshToken(refreshTokenRequest.getAccessToken());
                tokenService.updateRefreshToken(refreshTokenRequest.getRefreshToken(), newRefreshToken);

                System.out.println("ACCESS TOKEN IS EXPIRED");
                final String newAccessToken =
                        jwtUtil.doGenerateToken(jwtUtil.getSubject(refreshTokenRequest.getRefreshToken()));
                new LoginResponse(newAccessToken, newRefreshToken,"Success!");
            } else {
                System.out.println("ACCESS TOKEN IS NOT EXPIRED");
            }
        }
        return new LoginResponse(
                refreshTokenRequest.getAccessToken(), refreshTokenRequest.getRefreshToken(),"");

    }
}
