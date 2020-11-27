package ru.innovat.service.authorization;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.authorization.TokenDao;
import ru.innovat.dao.authorization.UserDao;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.authorization.VerificationToken;


import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class NewUserService {
    final TokenDao tokenDao;
    final EmailService emailService;
    final UserDao userDao;
    final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public void saveUser(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        userDao.add(appUser);
        saveToken(appUser);
    }

    @Transactional
    public void deleteToken(int id) {
        tokenDao.delete(id);
    }

    @Transactional
    public void saveToken(AppUser appUser) {
        VerificationToken verificationToken = new VerificationToken(UUID.randomUUID().toString(), appUser);
        tokenDao.add(verificationToken);
        emailService.sendEmail(appUser.getEMail(),verificationToken);
    }

    @Transactional
    public VerificationToken findByToken(String Token) {
        return tokenDao.findByToken(Token);
    }

    @Transactional
    public AppUser getUser(String token) {
        VerificationToken verificationToken = tokenDao.findByToken(token);
        if (token != null) {
            return verificationToken.getUser();
        }
        return null;
    }

    @Transactional
    public String emailVerification(String token) {
        VerificationToken verificationToken = findByToken(token);
        if (verificationToken != null) {
            if (verificationToken.getExpiryDate().after(new Date())) {
                AppUser appUser = userDao.findByUsername(verificationToken.getUser().getUsername());
                appUser.setEnabled(true);
                userDao.update(appUser);
                deleteToken(verificationToken.getId_token());
                return "Почта подтвержденна";
            } else {
                saveToken(verificationToken.getUser());
                deleteToken(verificationToken.getId_token());
                return "Время действия ссылки истекло, мы отправили на вашу почту новое поддтвержение";
            }
        } else {
            return  "Данная ссылка не действительна либо сломана";
        }
    }

    public boolean checkUsername(String username) {
        return userDao.findByUsername(username) != null;
    }

    public boolean checkEmail(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Transactional
    public String checkAccount(AppUser appUser) {
        if (checkEmail(appUser.getEMail())) return "Такая почта уже существует";
        if (checkUsername(appUser.getUsername())) return "Имя пользователя уже занято";
        if (!(appUser.getPassword().equals(appUser.getPasswordConfirm()))) return "Пароли не совпадают";
        return null;
    }
}
