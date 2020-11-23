package ru.innovat.controller.major.organization;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ConnectionOrganizationService {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final ConnectionService connectionService;

    @GetMapping("organization/{id}/con")
    public String oneOrganizationAddCon(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.organizationAllConnection(id);
        Connect con = new Connect();
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
        model.addAttribute("con", con);
        return "organization/addOrganizationCon";
    }

    @PostMapping(value = "/organization/{id}/con")
    public String organizationAddCon(@PathVariable("id") int id, @ModelAttribute Organization organization, Connect con, Model model){
        connectionService.addConnections(con,organizationService.organizationAllConnection(id));
        model.addAttribute("organization", organization);
        model.addAttribute("con", con);
        return "redirect:";
    }
}

