package ru.innovat.controller.major.organization;

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
public class ConnectionOrganizationController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final ConnectionService connectionService;

    @GetMapping("organization/{id}/con")
    public String organizationAddConnectionPage(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.organizationAllConnection(id);
        Connect connect = new Connect();
        List<Event> eventList = eventService.eventList();
        List<Project> projectList = projectService.projectList();
        List<Person> personList = personService.personList();
        eventList.removeAll(organization.getEvents());
        personList.removeAll(organization.getPersons());
        projectList.removeAll(organization.getProjects());
        model.addAttribute("organization", organization);
        model.addAttribute("events", eventList);
        model.addAttribute("projects", projectList);
        model.addAttribute("persons", personList);
        model.addAttribute("con", connect);
        return "organization/addOrganizationCon";
    }

    @PostMapping(value = "/organization/{id}/con")
    public String organizationAddConnection(@PathVariable("id") int id, @ModelAttribute Organization organization, Connect connect){
        connectionService.addConnections(connect,organizationService.organizationAllConnection(id));
        return "redirect:";
    }
}

