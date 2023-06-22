package com.se.rental.service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);
}
