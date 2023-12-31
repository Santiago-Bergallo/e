package com.example.eCommerce.v2.service;

import com.example.eCommerce.v2.exceptions.EmailFailureException;
import com.example.eCommerce.v2.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Value("${email.from.address}")
    private String fromAddress;

    @Value("${app.frontend.url}")
    private String url;

    @Autowired
    JavaMailSender javaMailSender;


    public SimpleMailMessage simpleMailMessage() {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(fromAddress);
        return mail;
    }

    public void verificationMessage(VerificationToken token) throws EmailFailureException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(token.getLocalUser().getEmail());
        mailMessage.setSubject("Verify email to activate your account.");
        mailMessage.setText("Please follow the link to verify your account \n" + url + "/verify?token="+token.getToken());
        try {
            javaMailSender.send(mailMessage);
        }
        catch (MailException ex) {
            throw new EmailFailureException();
        }




    }



}
