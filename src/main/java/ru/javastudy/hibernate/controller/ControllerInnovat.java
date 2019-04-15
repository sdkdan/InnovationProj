package ru.javastudy.hibernate.controller;

import javassist.bytecode.stackmap.BasicBlock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.javastudy.hibernate.models.Organization;
import ru.javastudy.hibernate.models.Person;
import ru.javastudy.hibernate.models.Project;
import ru.javastudy.hibernate.models.Event;
import ru.javastudy.hibernate.service.OrganizationSevice;
import ru.javastudy.hibernate.service.PersonService;
import ru.javastudy.hibernate.service.ProjectService;
import ru.javastudy.hibernate.service.EventService;


import java.sql.SQLDataException;
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
        return "add";
    }

    @RequestMapping(value = "/person/add", method = RequestMethod.POST)
    public String addPerson(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        personService.addPerson(person);
        return "added";
    }

    @GetMapping("person/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model) {
        personService.deletePerson(id);
        return "redirect:/person";
    }


    @GetMapping("person/{id}")
    public String onePerson(@PathVariable("id") int id, Model model){
        Person person = personService.findPerson(id);
        model.addAttribute("person", person);
        return "onePerson";
    }


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

    @GetMapping("organization/delete/{id}")
    public String deleteOrganization(@PathVariable("id") int id, Model model) {
        organizationService.deleteOrganiztion(id);
        return "redirect:/organization";
    }

}
