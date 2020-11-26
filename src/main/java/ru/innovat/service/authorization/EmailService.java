package ru.innovat.service.authorization;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.innovat.models.authorization.VerificationToken;

@Service("emailService")
public class EmailService {
    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email, VerificationToken verificationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Завершите регистрацию");
        mailMessage.setText("Что бы подтвердить почту перейдите по ссылке : "
                + "http://localhost:8080/confirm-account?token=" + verificationToken.getToken());
        javaMailSender.send(mailMessage);
    }
}
