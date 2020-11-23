
package ru.innovat.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
//import ru.innovat.Search.PersonSearch;
import ru.innovat.search.PersonSearch;
import ru.innovat.models.*;
import ru.innovat.service.*;
import ru.innovat.models.utils.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class PersonController {
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final PersonService personService;
    private final SearchService searchService;
    private final ConnectionService connectionService;

    @GetMapping(value = "/person/add")
    public String getAddPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/add";
    }

    @PostMapping(value = "/person/add")
    public String addPerson(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        personService.addPerson(person);
        return "redirect:" + person.getId_person();
    }

    @GetMapping("person/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
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

    @PostMapping(value = "/person/{id}/con")
    public String personAddCon(@PathVariable("id") int id, @ModelAttribute Person person, Connect personcon, Model model){
        model.addAttribute("person", person);
        model.addAttribute("personcon", personcon);
        connectionService.addConnections(personcon, personService.findPerson(id));
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
