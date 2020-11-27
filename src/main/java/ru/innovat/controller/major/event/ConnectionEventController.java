package ru.innovat.controller.major.event;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.major.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ConnectionEventController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final ConnectionService connectionService;

    @GetMapping("event/{id}/con")
    public String eventAddConnectionPage(@PathVariable("id") int id, Model model) {
        Connect connect = new Connect();
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
        model.addAttribute("con", connect);
        return "event/addEventCon";
    }

    @PostMapping(value = "/event/{id}/con")
    public String eventAddConnection(@PathVariable("id") int id, @ModelAttribute Event event, Connect connect){
        connectionService.addConnections(connect,eventService.findEvent(id));
        return "redirect:";
    }
}
