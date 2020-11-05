package ru.innovat.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.dao.utils.WebUtils;
import ru.innovat.models.AppUser;
import ru.innovat.models.Blocked;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
//import ru.innovat.service.UserDetailsServiceImpl;
import ru.innovat.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Date;
import java.util.List;

@Controller
public class AdminController {

    //private final UserService userService;
    private final UserService userService;

    public AdminController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        List<AppUser> users = userService.userList();
        users.remove(userService.findUserByUsername(userInfo));
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("users", users);

        return "adminPage";
    }

    @GetMapping("/admin/user/{id}")
    public String oneUser(@PathVariable("id") int id, Model model, Principal principal){
        Connect connect = new Connect();
        model.addAttribute("user",userService.findUser(id));
        model.addAttribute("roles", userService.roleList());
        model.addAttribute("connect", connect);
        return "oneUser";
    }

    @RequestMapping(value = "/admin/user/{id}", method = RequestMethod.POST)
    public String userAddRole(@PathVariable("id") int id, @ModelAttribute AppUser user, Connect connect, Model model) {
        model.addAttribute("user", user );
        model.addAttribute("connect", connect );
        if (connect.getRole_id() >= 1) {
            userService.updateUser(userService.setRole(userService.getRoleById(connect.getRole_id()),id));
        }

        return "redirect:/admin/user/" + id;
    }

    @RequestMapping(value = "/admin/user/{id}/ban", method = RequestMethod.GET)
    public String getBanUser(@PathVariable("id") int id, Model model){
        model.addAttribute("user",userService.findUser(id));
        model.addAttribute("blocked",new Blocked());
        return "banUser";
    }

    @RequestMapping(value = "/admin/user/{id}/saveban", method = RequestMethod.POST)
    public String banUser(@PathVariable("id") int id, @Valid Blocked blocked, Model model) {
        AppUser user = userService.findUser(id);
        blocked.setAppUser(user);
        System.out.println(blocked.getComment());
        System.out.println(blocked.getEndDate());
        blocked.setStartDate(new Date());
        userService.addBlocked(blocked);
        user.setBlocked(userService.getBlocked(blocked.getId_blocked()));
        userService.update(user);
        return "redirect:/admin/user/" + id;
    }

    @RequestMapping(value = "/admin/user/{id}/unban", method = RequestMethod.GET)
    public String unban(@PathVariable("id") int id, Model model) {
        AppUser appUser = userService.findUser(id);
        appUser.setBlocked(null);
        userService.update(appUser);
        return "redirect:/admin/user/" + id;
    }
}