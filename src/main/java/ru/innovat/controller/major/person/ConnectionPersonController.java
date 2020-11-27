package ru.innovat.controller.major.person;

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
public class ConnectionPersonController {
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final PersonService personService;
    private final ConnectionService connectionService;

    @GetMapping("person/{id}/con")
    public String personAddConnectionPage(@PathVariable("id") int id, Model model) {
        Person person = personService.personAllConnections(id);
        Connect connect = new Connect();
        List<Event> eventList = eventService.eventList();
        List<Project> projectList = projectService.projectList();
        List<Organization> organizationList = organizationService.organizationList();
        eventList.removeAll(person.getEvents());
        projectList.removeAll(person.getProjects());
        organizationList.removeAll(person.getOrganizations());
        model.addAttribute("list", eventList);
        model.addAttribute("projectlist", projectList);
        model.addAttribute("orglist", organizationList);
        model.addAttribute("personcon", connect);
        model.addAttribute("person", person);
        return "person/addPersonCon";
    }

    @PostMapping(value = "/person/{id}/con")
    public String personAddConnection(@PathVariable("id") int id, @ModelAttribute Person person, Connect connect){
        connectionService.addConnections(connect, personService.personAllConnections(id));
        return "redirect:";
    }
}
