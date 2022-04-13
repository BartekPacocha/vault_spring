package com.example.vault_spring.mail.services;

public interface EmailSender {

    void sendEmail(final String to, final String subject, final String content);
}
