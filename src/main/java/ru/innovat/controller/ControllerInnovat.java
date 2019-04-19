package ru.innovat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.*;
import ru.innovat.service.*;


import javax.validation.Valid;
import java.util.List;


@org.springframework.stereotype.Controller    // This means that this class is a Controller
public class ControllerInnovat {
    @Autowired
    private PersonService personService;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EventService eventService;
    @Autowired
    private OrganizationSevice organizationService;


    @RequestMapping(value = "/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/menu")
    public String menu() {
        return "menu";
    }

    @RequestMapping(value = "/person")
    public String listPerson(Model model) {
        List<Person> list = personService.personList();
        model.addAttribute("personList", list);
        return "person";
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.GET)
    public String getAddPerson(Model model) {
        model.addAttribute("person", new Person());
        return "add1";
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        personService.addPerson(person);
        return "added";
    }
    @GetMapping("/person/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        Person person = personService.findPerson(id);
        model.addAttribute("person", person);
        return "updatePerson";
    }
    @PostMapping("person/update/{id}")
    public String updateUser(@PathVariable("id") int id, @Valid Person person,
                             BindingResult result, Model model) {

        person.setId_person(id);
        int id1 = person.getId_person();
        personService.updatePerson(person);
        return "person";
    }

//    @RequestMapping(value = "/person/update/{id}", method = RequestMethod.GET)
//    public String getUpdatePerson( @PathVariable("id")int id,Model model, Person person) {
//        model.addAttribute("person", personService.findPerson(id));
//       // personService.updatePerson(person);
//        return "updatePerson";
//    }
//    @RequestMapping(value = "/person/update/{id}", method = RequestMethod.POST)
//    public String UpdatePerson(@ModelAttribute Person person,Model model) {
//        model.addAttribute("person", person);
//         personService.updatePerson(person);
//        return "redirect:/person";
//    }

//    @GetMapping(value = "person/update/{id}")
//    public String updateUser(@PathVariable("id") int id, Model model, Person person) {
//        model.addAttribute("person", person);
//        personService.updatePerson(person);
//        return "redirect:/updatePerson";
//    }

    @GetMapping("person/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        personService.deletePerson(id);
        return "redirect:/person";
    }


    @GetMapping("person/{id}")
    public String onePerson(@PathVariable("id") int id, Model model, Model model1){
        Person person = personService.findPerson(id);
        model.addAttribute("person", person);
        List<Event> event = eventService.eventList();
        model.addAttribute("list", event);
        return "onePerson";
    }

//    @RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
//    public String addEventToPerson(@PathVariable("id") @ModelAttribute("person") Person person, int id,Event event, Model model) {
//        model.addAttribute("list", event);
//        personService.findPerson(id).addEvent(event);
//        return "onePerson";
//    }
//    @RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
//    public String signupPost(@ModelAttribute("person") Person person,
//                             Model model, BindingResult result) {
//
//        if (!result.hasErrors()) {
//            personService.updatePerson(person);
//        }
//        return "redirect:/onePerson";
//
//    }
//@RequestMapping(value = "/person/{id}", method = RequestMethod.POST)
//public String addEventToPerson(@PathVariable("id")int id, @ModelAttribute Person person, @ModelAttribute Event event, Model model) {
//    model.addAttribute("list", event);
//    personService.findPerson(id).addEvent(event);
//    //person1.addEvent(event);
//    //person.addEvent(event);
//    return "onePerson";
//}



    @RequestMapping(value = "/project")
    public String listProject(Model model) {
        Iterable<Project> list = projectService.projectList();
        model.addAttribute("projectList", list);
        return "project";
    }

    @GetMapping("/project/delete/{id}")
    public String deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return "redirect:/project";
    }

    @GetMapping("/project/edit/{id}")
    public String showProjectForm(@PathVariable("id") int id, Model model) {
        Project project = projectService.findProject(id);
        model.addAttribute("project", project);
        return "updateProject";
    }
    @PostMapping("project/update/{id}")
    public String updateProject(@PathVariable("id") int id, @Valid Project project,
                                     BindingResult result, Model model) {
        project.setId_project(id);
        int id1 = project.getId_project();
        projectService.updateProject(project);
        return "project";
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public String getAddProject(Model model) {

        model.addAttribute("project", new Project());
        return "addproject";
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public String addProject(@ModelAttribute Project project, Model model) {
        model.addAttribute("project", project);
        projectService.addProject(project);
        return "addedproject";
    }

    @RequestMapping(value = "/event")
    public String listEvent(Model model) {
        List<Event> list = eventService.eventList();
        model.addAttribute("eventList", list);
        return "event";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.GET)
    public String getAddEvent(Model model) {
        model.addAttribute("event", new Event());
        return "addevent";
    }

    @RequestMapping(value = "/event/add", method = RequestMethod.POST)
    public String addEvent(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        eventService.addEvent(event);
        return "addedevent";
    }

    @GetMapping("/event/edit/{id}")
    public String showEventForm(@PathVariable("id") int id, Model model) {
        Event event = eventService.findEvent(id);
        model.addAttribute("event", event);
        return "updateEvent";
    }
    @PostMapping("event/update/{id}")
    public String updateEvent(@PathVariable("id") int id, @Valid Event event,
                                BindingResult result, Model model) {
        event.setId_event(id);
        int id1 = event.getId_event();
        eventService.updateEvent(event);
        return "event";
    }


    @RequestMapping(value = "/organization")
    public String listorganization(Model model) {
        List<Organization> list = organizationService.organizationList();
        model.addAttribute("organizationList", list);
        return "organization";
    }


    @RequestMapping(value = "/organization/add", method = RequestMethod.GET)
    public String getAddorganization(Model model) {
        model.addAttribute("organization", new Organization());
        return "addOrg";
    }

    @RequestMapping(value = "/organization/add", method = RequestMethod.POST)
    public String addOrganization(@ModelAttribute Organization organization, Model model) {
        model.addAttribute("organization", organization);
        organizationService.addOrganization(organization);
        return "addedOrg";
    }

    @GetMapping("/organization/edit/{id}")
    public String showOrganizationForm(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.findOrganization(id);
        model.addAttribute("organization", organization);
        return "updateOrganization";
    }
    @PostMapping("organization/update/{id}")
    public String updateOrganization(@PathVariable("id") int id, @Valid Organization organization,
                             BindingResult result, Model model) {
        organization.setId_organization(id);
        int id1 = organization.getId_organization();
        organizationService.updateOrganization(organization);
        return "organization";
    }



    @GetMapping("organization/delete/{id}")
    public String deleteOrganization(@PathVariable("id") int id, Model model) {
        organizationService.deleteOrganiztion(id);
        return "redirect:/organization";
    }

}
