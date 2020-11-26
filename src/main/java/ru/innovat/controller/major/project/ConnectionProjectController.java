package ru.innovat.controller.major.project;

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
public class ConnectionProjectController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final ConnectionService connectionService;

    @GetMapping("project/{id}/con")
    public String oneProjectAddCon(@PathVariable("id") int id, Model model) {
        Connect con = new Connect();
        Project project = projectService.projectAllConnections(id);
        List<Organization> organizationList = organizationService.organizationList();
        List<Event> eventList = eventService.eventList();
        List<Person> personList = personService.personList();
        eventList.removeAll(project.getEvents());
        personList.removeAll(project.getPersons());
        organizationList.removeAll(project.getOrganizations());
        model.addAttribute("organizations", organizationList);
        model.addAttribute("events", eventList);
        model.addAttribute("project", project);
        model.addAttribute("persons", personList);
        model.addAttribute("con", con);
        return "project/addProjectCon";
    }

    @PostMapping(value = "/project/{id}/con")
    public String eventAddCon(@PathVariable("id") int id, @ModelAttribute Project project, Connect con, Model model){
        model.addAttribute("project", project);
        model.addAttribute("con", con);
        connectionService.addConnections(con,projectService.findProject(id));
        return "redirect:";
    }
}
