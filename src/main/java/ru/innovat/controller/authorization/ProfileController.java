package ru.innovat.controller.authorization;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.service.authorization.UserService;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ProfileController {

    final UserService userService;

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
        user.setRole(appUser.getRole());
        userService.updateUser(user);
        return "redirect:";
    }
}