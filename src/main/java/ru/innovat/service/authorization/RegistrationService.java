package ru.innovat.service.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.authorization.TokenDao;
import ru.innovat.dao.authorization.UserDao;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.authorization.VerificationToken;
import ru.innovat.service.utils.DateExpired;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService {
    private final TokenDao tokenDao;
    private final EmailService emailService;
    private final UserDao userDao;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void saveUser(AppUser appUser) {
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        userDao.add(appUser);
        saveToken(appUser);
    }

    public void deleteToken(int id) {
        tokenDao.delete(id);
    }

    public void saveToken(AppUser appUser) {
        VerificationToken verificationToken = new VerificationToken(UUID.randomUUID().toString(), appUser);
        tokenDao.add(verificationToken);
        emailService.sendEmail(appUser.getEMail(), verificationToken);
    }

    public VerificationToken findByToken(String Token) {
        return tokenDao.findByToken(Token);
    }

    @Transactional
    public String emailVerification(String token) {
        VerificationToken verificationToken = findByToken(token);
        if (verificationToken != null) {
            if (DateExpired.isExpired(verificationToken.getExpiryDate())) {
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
            return "Данная ссылка не действительна либо сломана";
        }
    }

    public boolean checkUsername(String username) {
        return userDao.findByUsername(username) != null;
    }

    public boolean checkEmail(String email) {
        return userDao.findByEmail(email) != null;
    }

    @Transactional
    @Nullable
    public String registeredAccountStatus(AppUser appUser) {
        if (checkEmail(appUser.getEMail())) {
            return "Такая почта уже существует";
        }
        if (checkUsername(appUser.getUsername())) {
            return "Имя пользователя уже занято";
        }
        if (!(appUser.getPassword().equals(appUser.getPasswordConfirm()))) {
            return "Пароли не совпадают";
        }
        return null;
    }
}
