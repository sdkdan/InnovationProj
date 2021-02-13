package ru.innovat.controller.major.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.ConnectionService;
import ru.innovat.service.major.ProjectService;

@Controller
@RequiredArgsConstructor
public class ConnectionProjectController {

    private final ProjectService projectService;
    private final ConnectionService connectionService;

    @GetMapping("project/{id}/connect")
    public String oneProjectAddCon(@PathVariable("id") int id, Model model) {
        Project project = projectService.projectAllConnections(id);
        model.addAttribute("organizations", connectionService.removeConnectionsFormOrganizationList(project
                           .getOrganizations()));
        model.addAttribute("events", connectionService.removeConnectionsFormEventList(project.getEvents()));
        model.addAttribute("persons", connectionService.removeConnectionsFormPersonList(project.getPersons()));
        model.addAttribute("project", project);
        model.addAttribute("con", new Connect());
        return "project/addProjectCon";
    }

    @PostMapping(value = "/project/{id}/connect")
    public String eventAddCon(@PathVariable("id") int id, @ModelAttribute Project project, Connect connect) {
        connectionService.addConnections(connect, projectService.projectAllConnections(id));
        return "redirect:";
    }
}
