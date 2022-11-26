package com.example.elobit.mail.mailService;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
