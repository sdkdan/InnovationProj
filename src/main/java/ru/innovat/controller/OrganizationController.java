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
import ru.innovat.search.OrganizationSearch;
import ru.innovat.service.EventService;
import ru.innovat.service.OrganizationSevice;
import ru.innovat.service.PersonService;
import ru.innovat.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrganizationController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationSevice organizationService;
    private final OrganizationSearch organizationSearch;

    public OrganizationController(PersonService personService, ProjectService projectService, EventService eventService, OrganizationSevice organizationService, OrganizationSearch organizationSearch) {
        this.personService = personService;
        this.projectService = projectService;
        this.eventService = eventService;
        this.organizationService = organizationService;
        this.organizationSearch = organizationSearch;
    }

    @RequestMapping(value = "/organization")
    public String listorganization(String q, Model model) {
        List<Organization> searchResults;
        if (q != null) {
            if (q.length() > 0) {
                searchResults = organizationSearch.fuzzySearch(q);
            } else searchResults = organizationService.organizationList();
        } else searchResults = organizationService.organizationList();
        model.addAttribute("organizationList", searchResults);
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
    public String organizationAddCon(@PathVariable("id") int id, @ModelAttribute Organization organization, Connect con, Model model) throws Exception {
        model.addAttribute("organization", organization);
        model.addAttribute("con", con);
        System.out.println(con.getPerson_id());
        if (con.getPerson_id() >= 1) {
            organizationService.updateOrganization(organizationService.addPerson(personService.findPerson(con.getPerson_id()), id));
        }
        if (con.getProject_Id() >= 1) {
            organizationService.updateOrganization(organizationService.addPoject(projectService.findProject(con.getProject_Id()), id));
        }
        if (con.getEvent_Id() >= 1) {
            organizationService.updateOrganization(organizationService.addEvent(eventService.findEvent(con.getEvent_Id()), id));
        }
        return "organization" + id;
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
        return "redirect:organization/" + organization.getId_organization();
    }
}
