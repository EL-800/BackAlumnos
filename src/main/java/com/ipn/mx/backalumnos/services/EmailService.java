package com.ipn.mx.backalumnos.services;

public interface EmailService {
    void sendEmail(String to, String subject, String text);
}
