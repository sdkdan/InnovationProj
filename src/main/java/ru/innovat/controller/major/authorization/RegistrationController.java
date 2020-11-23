package ru.innovat.controller.major.authorization;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.AppUser;
import ru.innovat.service.NewUserService;
import ru.innovat.service.UserService;

import java.security.Principal;


@Controller
@AllArgsConstructor
public class RegistrationController {
    private final NewUserService newUserService;


    @GetMapping(value = "/login")
    public String loginPage() {

        return "registration/loginPage";
    }

    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "registration/logoutSuccessfulPage";
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        model.addAttribute("userInfo", userInfo);
        String message = "Hi " + principal.getName() + "<br> You do not have permission to access this page!";
        model.addAttribute("message", message);
        return "registration/403Page";
    }


    @GetMapping(value = "/register")
    public String displayRegistration(Model model, AppUser userEntity) {
        model.addAttribute("userForm", userEntity);
        return "registration/register";
    }


    //Можно ли в одном сервисе вызываь другой
    //Дилема сильное слабое связываение и if
    @PostMapping(value = "/register")
    public String registerUser(Model model, AppUser appUser) {
        String checkAccount = newUserService.checkAccount(appUser);
        if(checkAccount != null) {
            model.addAttribute("message",checkAccount);
            return "error";
        }
        newUserService.saveUser(appUser);
        model.addAttribute("emailId", appUser.getEMail());
        return "registration/successfulRegistration";
    }

//, method = {RequestMethod.GET, RequestMethod.POST}
    @GetMapping(value = "/confirm-account")
    public String confirmUserAccount(Model model, @RequestParam("token") String token) {
        model.addAttribute("message",newUserService.emailVerification(token));
        return "registration/accountVerified";
    }
}