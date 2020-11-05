package ru.innovat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.innovat.dao.MessagesDao;
import ru.innovat.models.*;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.MessagesService;
import ru.innovat.service.UserService;

import java.util.List;

@Controller
public class ChatController {

    final
    MessagesService messagesService;
    final
    UserService userService;

    public ChatController(MessagesService messagesService, UserService userService) {
        this.messagesService = messagesService;
        this.userService = userService;
    }


    @GetMapping("/support")
    public String oneEventAddCon(Model model){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("messages",messagesService.userMessages(userService.findUserByUsername(loggedInUser.getName()).getId_user()));
        return "support";
    }

//    @RequestMapping(value = "/admin/user/{id}/unban", method = RequestMethod.GET)
//    public String sendMessage(String q, Model model){
//
//        return "redirect:/support";
//    }



}
