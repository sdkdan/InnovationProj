//package ru.innovat.registration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.MessageSource;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//import ru.innovat.models.AppUser;
//import ru.innovat.service.UserService;
//
//import java.util.UUID;
//
//@Component
//public class RegistrationListener implements
//        ApplicationListener<OnRegistrationCompleteEvent> {
//
//    private final UserService service;
//    private final MessageSource messages;
//    private final JavaMailSender mailSender;
//
//    public RegistrationListener(JavaMailSender mailSender, MessageSource messages, UserService service) {
//        this.mailSender = mailSender;
//        this.messages = messages;
//        this.service = service;
//    }
//
//    @Override
//    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
//        this.confirmRegistration(event);
//    }
//
//    private void confirmRegistration(OnRegistrationCompleteEvent event) {
//        AppUser user = event.getUser();
//        String token = UUID.randomUUID().toString();
//        service.createVerificationTokenForUser(user, token);
//
//        String recipientAddress = user.getMail();
//        String subject = "Registration Confirmation";
//        String confirmationUrl
//                = event.getAppUrl() + "/regitrationConfirm.html?token=" + token;
//        String message = messages.getMessage("message.regSucc", null, event.getLocale());
//
//        SimpleMailMessage email = new SimpleMailMessage();
//        email.setTo(recipientAddress);
//        email.setSubject(subject);
//        email.setText(message + "\r\n" + "http://localhost:8080" + confirmationUrl);
//        mailSender.send(email);
//    }
//}