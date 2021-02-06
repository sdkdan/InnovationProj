package ru.innovat.controller.major.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Organization;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.ConnectionService;
import ru.innovat.service.major.OrganizationService;


@Controller
@RequiredArgsConstructor
public class ConnectionOrganizationController {
    private final OrganizationService organizationService;
    private final ConnectionService connectionService;

    @GetMapping("organization/{id}/connect")
    public String organizationAddConnectionPage(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.organizationAllConnection(id);
        model.addAttribute("events", connectionService.removeConnectionsFormEventList(organization.getEvents()));
        model.addAttribute("projects", connectionService.removeConnectionsFromProjectList(organization.getProjects()));
        model.addAttribute("persons", connectionService.removeConnectionsFormPersonList(organization.getPersons()));
        model.addAttribute("organization", organization);
        model.addAttribute("con", new Connect());
        return "organization/addOrganizationCon";
    }

    @PostMapping(value = "/organization/{id}/connect")
    public String organizationAddConnection(@PathVariable("id") int id, @ModelAttribute Organization organization, Connect connect) {
        connectionService.addConnections(connect, organizationService.organizationAllConnection(id));
        return "redirect:";
    }
}

