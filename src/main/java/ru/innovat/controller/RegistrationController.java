package ru.innovat.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.innovat.models.AppUser;

import ru.innovat.models.VerificationToken;
import ru.innovat.service.EmailService;
import ru.innovat.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.*;


import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


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
    public ModelAndView displayRegistration(ModelAndView modelAndView, AppUser userEntity) {
        modelAndView.addObject("userForm", userEntity);
        modelAndView.setViewName("registration/register");
        return modelAndView;
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView registerUser(ModelAndView modelAndView, AppUser appUser) {


        if (userService.checkEmail(appUser.getEMail())) {
            modelAndView.addObject("message", "This email already exists!");
            modelAndView.setViewName("error");
        } else {
            if (userService.checkUsername(appUser.getUsername())) {
                modelAndView.addObject("message", "This username already exists!");
                modelAndView.setViewName("error");
            } else {
                userService.saveUser(appUser);

                VerificationToken verificationToken = new VerificationToken(UUID.randomUUID().toString(), appUser);
                System.out.println(verificationToken.getExpiryDate());
                userService.saveToken(verificationToken);

                emailService.sendEmail(appUser.getEMail(), verificationToken);

                modelAndView.addObject("emailId", appUser.getEMail());

                modelAndView.setViewName("registration/successfulRegistration");
            }
        }
        return modelAndView;
    }


    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token") String Token) {
        VerificationToken verificationToken = userService.findByToken(Token);

        if (verificationToken != null) {

            if (verificationToken.getExpiryDate().compareTo(new Date()) > 0) {
                AppUser appUser = userService.findUserByUsername(verificationToken.getUser().getUsername());
                appUser.setEnabled(true);
                userService.update(appUser);
                userService.deleteToken(verificationToken.getId_token());
                modelAndView.setViewName("registration/accountVerified");
            } else {
                modelAndView.addObject("message", "Время действия ссылки истекло, мы отправили на вашу почту новое поддтвержение");
                VerificationToken newVerificationToken = new VerificationToken(UUID.randomUUID().toString(), verificationToken.getUser());
                System.out.println(newVerificationToken.getExpiryDate());
                userService.saveToken(newVerificationToken);
                userService.deleteToken(verificationToken.getId_token());
            }
        } else {
            modelAndView.addObject("message", "Данная ссылка не действительна либо сломана");
            modelAndView.setViewName("error");
        }


        return modelAndView;
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