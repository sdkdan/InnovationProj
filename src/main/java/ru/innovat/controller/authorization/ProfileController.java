package ru.innovat.controller.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.service.authorization.UserService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ProfileController {

    private final UserService userService;

    @GetMapping("/myprofile")
    public String profile(Model model, Principal principal) {
        AppUser user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/myprofile";
    }

    @GetMapping("/myprofile/edit")
    public String editProfile(Model model, Principal principal) {
        AppUser user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/editUser";
    }

    @GetMapping("/myprofile/editpassword")
    public String editPassword(Model model, Principal principal) {
        AppUser user = userService.findUserByUsername(principal.getName());
        model.addAttribute("user", user);
        return "user/editPassword";
    }

    @PostMapping("/myprofile/update")
    public String updateProfile(@ModelAttribute AppUser user, Principal principal) {
        AppUser appUser = userService.findUserByUsername(principal.getName());
        user.setId_user(appUser.getId_user());
        user.setPassword(appUser.getPassword());
        user.setRole(appUser.getRole());
        userService.updateUser(user);
        return "redirect:";
    }
}
