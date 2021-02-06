package ru.innovat.controller.major.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.innovat.models.major.Event;
import ru.innovat.search.EventSearch;
import ru.innovat.service.major.EventService;


@Controller
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;
    private final EventSearch searchService;

    @GetMapping(value = "/event")
    public String listEvent(String search, Model model) {
        model.addAttribute("eventList", searchService.fuzzySearch(search));
        return "event/event";
    }

    @GetMapping("event/{id}")
    public String oneEvent(@PathVariable("id") int id, Model model) {
        Event event = eventService.eventAllConnections(id);
        model.addAttribute("event", event);
        return "event/oneEvent";
    }

    @GetMapping("event/{id}/delete")
    public String deleteEvent(@PathVariable("id") int id) {
        eventService.deleteEvent(id);
        return "redirect:";
    }
}
