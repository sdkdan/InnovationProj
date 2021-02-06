package ru.innovat.controller.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.service.authorization.RegistrationService;

import java.security.Principal;


@Controller
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "registration/loginPage";
    }

    @GetMapping(value = "/logoutSuccessful")
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "registration/logoutSuccessfulPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        model.addAttribute("userInfo", principal.getName());
        model.addAttribute("message", "Hi " + principal.getName() + "<br> You do not have permission to access this page!");
        return "registration/403Page";
    }

    @GetMapping(value = "/register")
    public String registrationPage(Model model) {
        model.addAttribute("userForm", new AppUser());
        return "registration/register";
    }

    @PostMapping(value = "/register")
    public String registrationUser(Model model, @ModelAttribute AppUser userForm) {
        String checkAccount = registrationService.registeredAccountStatus(userForm);
        if (checkAccount != null) {
            model.addAttribute("message", checkAccount);
            return "error";
        }
        registrationService.saveUser(userForm);
        model.addAttribute("emailId", userForm.getEMail());
        return "registration/successfulRegistration";
    }

    @GetMapping(value = "/confirm-account")
    public String confirmUserAccount(Model model, @RequestParam("token") String token) {
        model.addAttribute("message", registrationService.emailVerification(token));
        return "registration/accountVerified";
    }
}