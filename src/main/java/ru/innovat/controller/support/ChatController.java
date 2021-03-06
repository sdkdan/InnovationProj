package ru.innovat.controller.support;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.support.Messages;
import ru.innovat.service.authorization.UserService;
import ru.innovat.service.support.MessagesService;

import java.security.Principal;
import java.util.Date;

@RequiredArgsConstructor
@Controller
public class ChatController {

    private final MessagesService messagesService;
    private final UserService userService;

    @GetMapping("/help")
    public String helpForm(Model model, Principal principal) {
        model.addAttribute("messages", messagesService.userMessages(userService.findUserByUsername(principal.getName())
                                                                                  .getId_user()));
        model.addAttribute("newMessage", new Messages());
        return "help";
    }

    @PostMapping(value = "/help/send")
    public String sendMessage(@ModelAttribute Messages newMessage, Principal principal) {
        newMessage.setAppUser(userService.findUserByUsername(principal.getName()));
        newMessage.setTime(new Date());
        newMessage.setUserMessage(true);
        messagesService.addMessage(newMessage);
        return "redirect:/help";
    }

    @GetMapping(value = "/support")
    public String userSupportList(Model model) {
        model.addAttribute("users", userService.roleUserList());
        return "support";
    }

    @GetMapping(value = "/support/{id}")
    public String userSupport(@PathVariable("id") int id, Model model) {
        model.addAttribute("newMessage", new Messages());
        model.addAttribute("messages", messagesService.userMessages(id));
        model.addAttribute("idUser", id);
        return "supportChat";
    }

    @PostMapping(value = "/support/{id}/send")
    public String userSupportSend(@ModelAttribute Messages newMessage, @PathVariable("id") int id) {
        newMessage.setAppUser(userService.findUser(id));
        newMessage.setTime(new Date());
        newMessage.setUserMessage(false);
        messagesService.addMessage(newMessage);
        return "redirect:";
    }

}
