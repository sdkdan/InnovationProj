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
import ru.innovat.search.OrganizationSearch;
import ru.innovat.service.EventService;
import ru.innovat.service.OrganizationService;
import ru.innovat.service.PersonService;
import ru.innovat.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class OrganizationController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final OrganizationSearch organizationSearch;


    @RequestMapping(value = "/organization")
    public String listorganization(String search, Model model) {
        model.addAttribute("organizationList", organizationSearch.searchListOrganization(search));
        return "organization/organization";
    }

    @RequestMapping(value = "/organization/add", method = RequestMethod.GET)
    public String getAddorganization(Model model) {
        model.addAttribute("organization", new Organization());
        return "organization/addOrg";
    }

    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public String addOrganization(@ModelAttribute Organization organization, Model model) {
        model.addAttribute("organization", organization);
        organizationService.addOrganization(organization);
        return "redirect:" + organization.getId_organization();
    }

    @GetMapping("organization/{id}/delete")
    public String deleteOrganization(@PathVariable("id") int id) {
        organizationService.deleteOrganiztion(id);
        return "redirect:organization";
    }

    @GetMapping("organization/{id}")
    public String oneOrganization(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.organizationAllConnection(id);
        model.addAttribute("organization", organization);
        return "organization/oneOrg";
    }

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

    @RequestMapping(value = "/organization/{id}/con", method = RequestMethod.POST)
    public String organizationAddCon(@PathVariable("id") int id, @ModelAttribute Organization organization, Connect con, Model model){
        organizationService.addConnections(con,id);
        model.addAttribute("organization", organization);
        model.addAttribute("con", con);
        return "redirect:";
    }

    @GetMapping("/organization/{id}/edit")
    public String showOrganizationForm(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.findOrganization(id);
        model.addAttribute("organization", organization);
        return "organization/updateOrganization";
    }

    @PostMapping("organization/{id}/update")
    public String updateOrganization(@PathVariable("id") int id, @Valid Organization organization, BindingResult bindingResult) {
        organization.setId_organization(id);
        organizationService.updateOrganization(organization);
        return "redirect:";
    }
}
