package com.se.rental.service;

public interface OtpService {

    void createOTP(String email);

    boolean canResetPassword(String email, String otp);
}
