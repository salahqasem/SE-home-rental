package com.se.rental.controller;



import com.se.rental.entity.dto.request.LoginRequest;
import com.se.rental.entity.dto.request.RefreshTokenRequest;
import com.se.rental.entity.dto.response.LoginResponse;
import com.se.rental.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authenticate")
@CrossOrigin
public class AuthController {

    private final AuthService authService;



    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        var loginResponse = authService.login(loginRequest);
        return ResponseEntity.ok().body(loginResponse);
    }

    @PostMapping("/refreshToken")
    public LoginResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

}

//{
//        "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbXJAbWl1LmVkdSIsImV4cCI6MTY4MTUxMDU0OCwiaWF0IjoxNjgxNTA5NDY4fQ.OwVre0u-AX1P2-nlnkDW42lHgvW4F9wDu_0SngHJi5pM7iV40A_ITUOT_D4SWWHuZ-JgvR1lJwHM7RdyjiLPPA",
//        "refreshToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbXJAbWl1LmVkdSIsImlhdCI6MTY4MTUwOTQ2OCwiZXhwIjoxNjgxNTc0MjY4fQ.VgCfhipfJnse4m2_zV-g4Rn5eqUnkViFtK5TOmY9nZV3hP_5czGQ9AuyakJHs9CIqwXZFAwxUvqwm9y0-l2j1Q"
//        }
