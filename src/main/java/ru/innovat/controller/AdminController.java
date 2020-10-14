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
import ru.innovat.models.Person;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.UserService;

import java.security.Principal;
import java.util.Collection;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    public AdminController(UserService userService){
        this.userService = userService;
    }
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {

        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String userInfo = loggedInUser.getName();
        List<AppUser> users = userService.userList();
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("users", users);

        return "adminPage";
    }

    @GetMapping("user/{id}")
    public String oneUser(@PathVariable("id") int id, Model model, Principal principal){
        Connect connect = new Connect();
        model.addAttribute("user",userService.findUser(id));
        model.addAttribute("roles", userService.roleList());
        model.addAttribute("connect", connect);
//        User loginedUser = (User) ((Authentication) principal).getPrincipal();
//        String userInfo = WebUtils.toString(loginedUser);
//        Collection<GrantedAuthority> authorities = (userService.findUserById(id)).getAuthorities();

        return "oneUser";
    }
    @RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
    public String personAddCon(@PathVariable("id") int id, @ModelAttribute AppUser user, Connect connect, Model model) {
        model.addAttribute("user", user );
        model.addAttribute("connect", connect );
        if (connect.getRole_id() >= 1) {
            userService.updateUser(userService.setRole(userService.getRoleById(connect.getRole_id()),id));
        }

        return "redirect:/user/" + id;
    }

}