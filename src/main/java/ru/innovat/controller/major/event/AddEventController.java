package ru.innovat.controller.major.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.Event;
import ru.innovat.models.utils.TypeEvent;
import ru.innovat.service.EventService;

import java.util.List;

@Controller
@AllArgsConstructor
public class AddEventController {
    EventService eventService;

    @GetMapping(value = "/event/add")
    public String getAddEvent(Model model) {
        model.addAttribute("event", new Event());
        List<TypeEvent> typeEventList = eventService.findAllTypeEvents();
        model.addAttribute("list", typeEventList);
        return "event/addevent";
    }

    @PostMapping(value = "/event/add")
    public String addEvent(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        eventService.addEvent(event);
        return "redirect:" + event.getId_event();
    }
}
