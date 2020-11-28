package ru.innovat.controller.major.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.innovat.models.major.Event;
import ru.innovat.models.utils.TypeEvent;
import ru.innovat.service.major.EventService;


import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class EditEventController {
    private final EventService eventService;

    @GetMapping("/event/{id}/edit")
    public String showEventForm(@PathVariable("id") int id, Model model) {
        Event event = eventService.findEvent(id);
        List<TypeEvent> typeEventList = eventService.findAllTypeEvents();
        model.addAttribute("list", typeEventList);
        model.addAttribute("event", event);
        return "event/updateEvent";
    }

    @PostMapping("event/{id}/update")
    public String updateEvent(@PathVariable("id") int id, @ModelAttribute Event event, BindingResult bindingResult) {
        event.setId_event(id);
        eventService.updateEvent(event);
        return "redirect:";
    }
}
