package ru.innovat.controller;
import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
        import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.EventService;
        import ru.innovat.service.OrganizationSevice;
        import ru.innovat.service.PersonService;
        import ru.innovat.service.ProjectService;
        import javax.validation.Valid;
        import java.util.List;

@Controller
public class EventController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationSevice organizationService;

    public EventController(PersonService personService, ProjectService projectService, EventService eventService, OrganizationSevice organizationService) {
        this.personService = personService;
        this.projectService = projectService;
        this.eventService = eventService;
        this.organizationService = organizationService;
    }
    @RequestMapping(value = "/event")
    public String listEvent(Model model) {
        List<Event> list = eventService.eventList();
        model.addAttribute("eventList", list);
        return "event";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.GET)
    public String getAddEvent(Model model) {
        model.addAttribute("event", new Event());
        List typeEventList = eventService.findAllTypeEvents();
        model.addAttribute("list", typeEventList);
        return "addevent";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);

        eventService.addEvent(event);
        return "redirect:/event/" + event.getId_event();
    }

    @GetMapping("event/{id}")
    public String oneEvent(@PathVariable("id") int id, Model model){
        Event event = eventService.eventAllConnections(id);
        model.addAttribute("event", event);
        return "oneEvent";
    }

    @GetMapping("event/{id}/con")
    public String oneEventAddCon(@PathVariable("id") int id, Model model){
        Connect con = new Connect();
        Event event = eventService.eventAllConnections(id);
        List<Organization> organizationList = organizationService.organizationList();
        List<Project> projectList = projectService.projectList();
        List<Person> personList = personService.personList();
        model.addAttribute("organizations", organizationList);
        model.addAttribute("event",event);
        model.addAttribute("projects",projectList);
        model.addAttribute("persons",personList);
        model.addAttribute("con",con);
        return "addEventCon";
    }
    @RequestMapping(value = "/event/{id}/con", method = RequestMethod.POST)
    public String eventAddCon(@PathVariable("id") int id, @ModelAttribute Event event, Connect con, Model model) throws Exception {
        model.addAttribute("event",event);
        model.addAttribute("con" , con);
        if(con.getProject_Id() >= 1){
            eventService.updateEvent(eventService.addProject(projectService.findProject(con.getProject_Id()),id));
        }
        if(con.getPerson_id() >= 1){
            eventService.updateEvent(eventService.addPerson(personService.findPerson(con.getPerson_id()),id));
        }
        if(con.getOrganization_Id() >= 1){
            eventService.updateEvent(eventService.addOrganization(organizationService.findOrganization(con.getOrganization_Id()),id));
        }
        return "redirect:/event/" + id;
    }


    @GetMapping("event/{id}/delete")
    public String deleteEvent(@PathVariable("id") int id, Model model) {
        eventService.deleteEvent(id);
        return "redirect:/event";
    }
    @GetMapping("/event/{id}/edit")
    public String showEventForm(@PathVariable("id") int id, Model model) {
        Event event = eventService.findEvent(id);
        model.addAttribute("event", event);
        return "updateEvent";
    }
    @PostMapping("event/{id}/update")
    public String updateEvent(@PathVariable("id") int id, @Valid Event event,
                              BindingResult result, Model model) {
        event.setId_event(id);
        eventService.updateEvent(event);
        return "redirect:/event/" + event.getId_event();
    }
}
