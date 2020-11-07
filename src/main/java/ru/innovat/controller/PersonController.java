
package ru.innovat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import ru.innovat.Search.PersonSearch;
import ru.innovat.search.PersonSearch;
import ru.innovat.models.*;
import ru.innovat.service.EventService;
import ru.innovat.service.OrganizationSevice;
import ru.innovat.service.PersonService;
import ru.innovat.service.ProjectService;
import ru.innovat.models.utils.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class PersonController {
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationSevice organizationService;
    private final PersonSearch personSearch;
    private final PersonService personService;

    public PersonController(PersonService personService, ProjectService projectService, EventService eventService, OrganizationSevice organizationService, PersonSearch personSearch) {
        this.personService = personService;
        this.projectService = projectService;
        this.eventService = eventService;
        this.organizationService = organizationService;
        this.personSearch = personSearch;
    }

    @RequestMapping(value = "/person")
    public String listPerson(String q, Model model) {
        List<Person> searchResults;
        if (q != null) {
            if (q.length() > 0) {
                searchResults = personSearch.fuzzySearch(q);
            } else searchResults = personService.personList();
        } else searchResults = personService.personList();
        model.addAttribute("personList", searchResults);
        return "person/person";
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.GET)
    public String getAddPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/add";
    }


    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        personService.addPerson(person);
        return "redirect:" + person.getId_person();
    }

    @GetMapping("person/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        personService.deleteSets(personService.findPerson(id));
        personService.deletePerson(id);
        return "redirect:/person";
    }

    @GetMapping("person/{id}")
    public String onePerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.personAllConnections(id));
        return "person/onePerson";
    }


    @GetMapping("person/{id}/con")
    public String onePersonCon(@PathVariable("id") int id, Model model) {
        Person person = personService.personAllConnections(id);
        Connect personConnect = new Connect();
        List<Event> eventList = eventService.eventList();
        List<Project> projectList = projectService.projectList();
        List<Organization> organizationList = organizationService.organizationList();
        eventList.removeAll(person.getEvents());
        projectList.removeAll(person.getProjects());
        organizationList.removeAll(person.getOrganizations());
        model.addAttribute("list", eventList);
        model.addAttribute("projectlist", projectList);
        model.addAttribute("orglist", organizationList);
        model.addAttribute("personcon", personConnect);
        model.addAttribute("person", person);
        return "person/addPersonCon";
    }


    @RequestMapping(value = "/person/{id}/con", method = RequestMethod.POST)
    public String personAddCon(@PathVariable("id") int id, @ModelAttribute Person person, Connect personcon, Model model) {
        model.addAttribute("person", person);
        model.addAttribute("personcon", personcon);
        if (personcon.getEvent_Id() >= 1) {
            personService.updatePerson(personService.addEvent(eventService.findEvent(personcon.getEvent_Id()), id));
        }
        if (personcon.getOrganization_Id() >= 1) {
            personService.updatePerson(personService.addOrganizatin(organizationService.findOrganization(personcon.getOrganization_Id()), id));
        }
        if (personcon.getProject_Id() >= 1) {
            personService.updatePerson(personService.addProject(projectService.findProject(personcon.getProject_Id()), id));
        }
        return "redirect:";
    }

    @GetMapping("/person/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Person person = personService.findPerson(id);
        model.addAttribute("person", person);
        return "person/updatePerson";
    }

    @PostMapping("person/{id}/update")
    public String updateUser(@PathVariable("id") int id, @Valid Person person,
                             BindingResult result, @ModelAttribute String event) {
        person.setId_person(id);
        personService.updatePerson(person);
        return "redirect:";
    }


}
