package ru.innovat.controller;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.Messages;
import ru.innovat.service.MessagesService;
import ru.innovat.service.UserService;

import java.util.Date;

@AllArgsConstructor
@Controller
public class ChatController {

    final MessagesService messagesService;
    final UserService userService;


    @GetMapping("/support")
    public String oneEventAddCon(Model model) {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("messages",messagesService.userMessages(userService.findUserByUsername(loggedInUser.getName()).getId_user()));
        model.addAttribute("newMessage",new Messages());
        return "support";
    }

    @PostMapping(value = "/support/send")
    public String sendMessage(@ModelAttribute Messages newMessage){
        newMessage.setAppUser(userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName()));
        newMessage.setTime(new Date());
        newMessage.setUser_message(true);
        messagesService.addMessage(newMessage);
        return "redirect:/support";
    }


    @GetMapping(value = "/supportu")
    public String userSupportList(Model model){
        model.addAttribute("users",   userService.roleUserList());
        return "supportChat";
    }


    @GetMapping(value = "/supportu/{id}")
    public String userSupport(@PathVariable("id") int id, Model model){
        model.addAttribute("newMessage", new Messages());
        model.addAttribute("messages",   messagesService.userMessages(id));
        model.addAttribute("idUser",   id);
        return "supportOneChat";
    }

    @PostMapping(value = "/supportu/{id}/send")
    public String userSupportSend(@ModelAttribute Messages newMessage, @PathVariable("id") int id){
        newMessage.setAppUser(userService.findUser(id));
        newMessage.setTime(new Date());
        newMessage.setUser_message(false);
        messagesService.addMessage(newMessage);
        return "redirect:";
    }

}
