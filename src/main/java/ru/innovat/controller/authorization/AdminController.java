package ru.innovat.controller.authorization;

import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.authorization.Blocked;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.authorization.UserService;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class AdminController {

    private final UserService userService;


    @GetMapping(value = "/admin")
    public String adminPage(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        List<AppUser> users = userService.userList();
        users.remove(userService.findUserByUsername(userInfo));
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("users", users);
        return "user/adminPage";
    }

    @GetMapping("/admin/user/{id}")
    public String oneUser(@PathVariable("id") int id, Model model) {
        Connect connect = new Connect();
        model.addAttribute("user", userService.findUser(id));
        model.addAttribute("roles", userService.roleList());
        model.addAttribute("connect", connect);
        return "user/oneUser";
    }

    @PostMapping(value = "/admin/user/{id}")
    public String userAddRole(@PathVariable("id") int id, @ModelAttribute AppUser user, Connect connect, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("connect", connect);

        userService.updateUser(userService.setRole(userService.getRoleById(connect.getRole_id()), id));

        return "redirect:" + id;
    }

    @GetMapping(value = "/admin/user/{id}/ban")
    public String getBanUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.findUser(id));
        model.addAttribute("blocked", new Blocked());
        return "user/banUser";
    }

    @PostMapping(value = "/admin/user/{id}/saveban")
    public String banUser(@PathVariable("id") int id, @Valid Blocked blocked) {
        AppUser user = userService.findUser(id);
        blocked.setAppUser(user);
        blocked.setStartDate(new Date());
        userService.addBlocked(blocked);
        user.setBlocked(userService.getBlocked(blocked.getId_blocked()));
        userService.update(user);
        return "redirect:";
    }

    @GetMapping(value = "/admin/user/{id}/unban")
    public String unban(@PathVariable("id") int id) {
        AppUser appUser = userService.findUser(id);
        appUser.setBlocked(null);
        userService.update(appUser);
        return "redirect:";
    }

    @GetMapping(value = "/menu")
    public String menu() {
        return "menu";
    }
}