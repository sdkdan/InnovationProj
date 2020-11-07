package ru.innovat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.AppUser;
import ru.innovat.models.VerificationToken;
import ru.innovat.service.EmailService;
import ru.innovat.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.UUID;


@Controller
public class RegistrationController {

    private final UserService userService;
    private final EmailService emailService;


    public RegistrationController(UserService userService, EmailService emailService) {
        this.userService = userService;
        this.emailService = emailService;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcomePage(Model model) {
        model.addAttribute("title", "Welcome");
        model.addAttribute("message", "This is welcome page!");
        return "registration/welcomePage";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {

        return "registration/loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "registration/logoutSuccessfulPage";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
            String userInfo = loggedInUser.getName();
            model.addAttribute("userInfo", userInfo);

            String message = "Hi " + principal.getName() //
                    + "<br> You do not have permission to access this page!";
            model.addAttribute("message", message);
        }

        return "registration/403Page";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String displayRegistration(Model model, AppUser userEntity) {
        model.addAttribute("userForm", userEntity);
        return "registration/register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(Model model, AppUser appUser) {


        if (userService.checkEmail(appUser.getEMail())) {
            model.addAttribute("message", "Такая почта уже существует");
            return "error";
        } else {
            if (userService.checkUsername(appUser.getUsername())) {
                model.addAttribute("message", "Имя пользователя уже занято");
                return  "error";
            } else {
                if(!(appUser.getPassword().equals(appUser.getPasswordConfirm()))) {
                    model.addAttribute("message", "Пароли не совпадают");
                    return  "error";
                }
                userService.saveUser(appUser);

                VerificationToken verificationToken = new VerificationToken(UUID.randomUUID().toString(), appUser);
                System.out.println(verificationToken.getExpiryDate());
                userService.saveToken(verificationToken);

                emailService.sendEmail(appUser.getEMail(), verificationToken);

                model.addAttribute("emailId", appUser.getEMail());

                return "registration/successfulRegistration";
            }
        }
    }


    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(Model model, @RequestParam("token") String Token) {
        VerificationToken verificationToken = userService.findByToken(Token);

        if (verificationToken != null) {

            if (verificationToken.getExpiryDate().compareTo(new Date()) > 0) {
                AppUser appUser = userService.findUserByUsername(verificationToken.getUser().getUsername());
                appUser.setEnabled(true);
                userService.update(appUser);
                userService.deleteToken(verificationToken.getId_token());
                model.addAttribute("registration/accountVerified");
            } else {
                model.addAttribute("message", "Время действия ссылки истекло, мы отправили на вашу почту новое поддтвержение");
                VerificationToken newVerificationToken = new VerificationToken(UUID.randomUUID().toString(), verificationToken.getUser());
                System.out.println(newVerificationToken.getExpiryDate());
                userService.saveToken(newVerificationToken);
                userService.deleteToken(verificationToken.getId_token());
            }
        } else {
            model.addAttribute("message", "Данная ссылка не действительна либо сломана");
            return "error";
        }
        return "error";
    }

    @GetMapping("myprofile")
    public String profile(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        AppUser user = userService.findUserByUsername(userInfo);
        model.addAttribute("user", user);
        return "user/myprofile";
    }

    @GetMapping("myprofile/edit")
    public String editProfile(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        AppUser user = userService.findUserByUsername(userInfo);
        model.addAttribute("user", user);
        return "user/editUser";
    }

    @GetMapping("myprofile/editpassword")
    public String editPassword(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        AppUser user = userService.findUserByUsername(userInfo);
        model.addAttribute("user", user);
        return "user/editPassword";
    }


    @PostMapping("myprofile/update")
    public String updateProfile(@Valid AppUser user) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        AppUser appUser = userService.findUserByUsername(userInfo);
        user.setId_user(appUser.getId_user());
        user.setPassword(appUser.getPassword());
        user.setRole(appUser.getId_role());
        userService.updateUser(user);
        return "redirect:";
    }
}