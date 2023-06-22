package com.se.rental.service.impl;

import com.se.rental.repository.OtpRepository;
import com.se.rental.service.EmailService;
import com.se.rental.service.OtpService;
import com.se.rental.entity.Otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    @Autowired
    OtpRepository otpRepository;

    @Autowired
    EmailService emailService;
    private static final Random RANDOM = new Random();
    private static final int MIN = 100000;
    private static final int MAX = 999999;

    @Override
    public void createOTP(String email) {
        Otp otp = new Otp();
        otp.setOtp(RANDOM.nextInt(MAX - MIN + 1) + MIN);
        otp.setEmail(email);

        otpRepository.save(otp);
        System.out.println("OTP: " + otp);
        emailService.sendEmail(email, "Reset password", "Your OTP: " + otp);
    }

    @Override
    public boolean canResetPassword(String email, String otp) {
        Optional<Otp> o = otpRepository.findByEmailAndOtp(email, otp);

        if (!o.isPresent()) {
            return false;
        }



        return LocalDateTime.now().getMinute() - o.get().getCreatedData().getMinute() <= 60;

    }
}
