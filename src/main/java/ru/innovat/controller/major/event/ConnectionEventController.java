package ru.innovat.controller.major.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Event;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.ConnectionService;
import ru.innovat.service.major.EventService;

@Controller
@RequiredArgsConstructor
public class ConnectionEventController {

    private final EventService eventService;
    private final ConnectionService connectionService;

    @GetMapping("event/{id}/connect")
    public String eventAddConnectionPage(@PathVariable("id") int id, Model model) {
        Event event = eventService.eventAllConnections(id);
        model.addAttribute("organizations", connectionService.removeConnectionsFormOrganizationList(event
                                                                .getOrganizations()));
        model.addAttribute("projects", connectionService.removeConnectionsFromProjectList(event.getProjects()));
        model.addAttribute("persons", connectionService.removeConnectionsFormPersonList(event.getPersons()));
        model.addAttribute("event", event);
        model.addAttribute("con", new Connect());
        return "event/addEventCon";
    }

    @PostMapping(value = "/event/{id}/connect")
    public String eventAddConnection(@PathVariable("id") int id, @ModelAttribute Event event, Connect connect) {
        connectionService.addConnections(connect, eventService.eventAllConnections(id));
        return "redirect:";
    }
}
