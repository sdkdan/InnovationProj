package ru.innovat.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.models.utils.TypeEvent;
import ru.innovat.search.EventSearch;
import ru.innovat.service.EventService;
import ru.innovat.service.OrganizationService;
import ru.innovat.service.PersonService;
import ru.innovat.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class EventController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;


    @RequestMapping(value = "/event")
    public String listEvent(String search, Model model) {
        model.addAttribute("eventList", eventService.searchEventList(search));
        return "event/event";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.GET)
    public String getAddEvent(Model model) {
        model.addAttribute("event", new Event());
        List<TypeEvent> typeEventList = eventService.findAllTypeEvents();
        model.addAttribute("list", typeEventList);
        return "event/addevent";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        eventService.addEvent(event);
        return "redirect:" + event.getId_event();
    }

    @GetMapping("event/{id}")
    public String oneEvent(@PathVariable("id") int id, Model model) {
        Event event = eventService.eventAllConnections(id);
        model.addAttribute("event", event);
        return "event/oneEvent";
    }

    @GetMapping("event/{id}/con")
    public String oneEventAddCon(@PathVariable("id") int id, Model model) {
        Connect con = new Connect();
        Event event = eventService.eventAllConnections(id);
        List<Organization> organizationList = organizationService.organizationList();
        List<Project> projectList = projectService.projectList();
        List<Person> personList = personService.personList();
        projectList.removeAll(event.getProjects());
        personList.removeAll(event.getPersons());
        organizationList.removeAll(event.getOrganizations());
        model.addAttribute("organizations", organizationList);
        model.addAttribute("event", event);
        model.addAttribute("projects", projectList);
        model.addAttribute("persons", personList);
        model.addAttribute("con", con);
        return "event/addEventCon";
    }

    @RequestMapping(value = "/event/{id}/con", method = RequestMethod.POST)
    public String eventAddCon(@PathVariable("id") int id, @ModelAttribute Event event, Connect con, Model model){
        model.addAttribute("event", event);
        model.addAttribute("con", con);
        eventService.addConnections(con,id);
        return "redirect:";
    }


    @GetMapping("event/{id}/delete")
    public String deleteEvent(@PathVariable("id") int id) {
        eventService.deleteEvent(id);
        return "redirect:";
    }

    @GetMapping("/event/{id}/edit")
    public String showEventForm(@PathVariable("id") int id,  Model model) {
        Event event = eventService.findEvent(id);
        List<TypeEvent> typeEventList = eventService.findAllTypeEvents();
        model.addAttribute("list", typeEventList);
        model.addAttribute("event", event);
        return "event/updateEvent";
    }

    @PostMapping("event/{id}/update")
    public String updateEvent(@PathVariable("id") int id, @Valid Event event, BindingResult bindingResult) {
        event.setId_event(id);
        eventService.updateEvent(event);
        return "redirect:";
    }
}
