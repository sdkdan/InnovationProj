package ru.innovat.service.authorization;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.innovat.models.authorization.VerificationToken;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender javaMailSender;

    public void sendEmail(String email, VerificationToken verificationToken) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Завершите регистрацию");
        mailMessage.setText("Что бы подтвердить почту перейдите по ссылке : "
                + "http://localhost:8080/confirm-account?token=" + verificationToken.getToken());
        javaMailSender.send(mailMessage);
    }
}
