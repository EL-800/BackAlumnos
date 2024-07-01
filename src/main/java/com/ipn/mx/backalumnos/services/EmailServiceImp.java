package com.ipn.mx.backalumnos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.tools.JavaFileManager;

@Service
public class EmailServiceImp implements  EmailService{

    @Autowired
    private JavaMailSender mailSender;
    @Override
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage simpleEmail = new SimpleMailMessage();
        simpleEmail.setTo(to);
        simpleEmail.setSubject(subject);
        simpleEmail.setText(text);
        mailSender.send(simpleEmail);
    }
}
