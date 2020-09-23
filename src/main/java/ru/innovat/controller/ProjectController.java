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
import ru.innovat.service.EventService;
import ru.innovat.service.OrganizationSevice;
import ru.innovat.service.PersonService;
import ru.innovat.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ProjectController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationSevice organizationService;

    public ProjectController(PersonService personService, ProjectService projectService, EventService eventService, OrganizationSevice organizationService) {
        this.personService = personService;
        this.projectService = projectService;
        this.eventService = eventService;
        this.organizationService = organizationService;
    }
    @RequestMapping(value = "/project")
    public String listProject(Model model) {
        Iterable<Project> list = projectService.projectList();
        model.addAttribute("projectList", list);
        return "project";
    }

    @GetMapping("/project/{id}/delete")
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
        return "redirect:/project/" + project.getId_project();
    }



    @GetMapping("project/{id}")
    public String oneProject(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.projectAllConnections(id));

        return "oneProject";
    }

    @GetMapping("project/{id}/con")
    public String oneProjectAddCon(@PathVariable("id") int id, Model model){
        Connect con = new Connect();
        Project project = projectService.projectAllConnections(id);
        List<Organization> organizationList = organizationService.organizationList();
        List<Event> eventList = eventService.eventList();
        List<Person> personList = personService.personList();
        model.addAttribute("organizations", organizationList);
        model.addAttribute("events",eventList);
        model.addAttribute("project",project);
        model.addAttribute("persons",personList);
        model.addAttribute("con",con);
        return "addProjectCon";
    }

    @RequestMapping(value = "/project/{id}/con", method = RequestMethod.POST)
    public String eventAddCon(@PathVariable("id") int id, @ModelAttribute Project project, Connect con, Model model) throws Exception {
        model.addAttribute("project", project);
        model.addAttribute("con" , con);
        if(con.getPerson_id() >= 1){
            projectService.updateProject(projectService.addPerson(personService.findPerson(con.getPerson_id()),id));
        }
        if(con.getOrganization_Id() >= 1){
            projectService.updateProject(projectService.addOrganization(organizationService.findOrganization(con.getOrganization_Id()),id));
        }
        if(con.getEvent_Id() >= 1){
            projectService.updateProject(projectService.addEvent(eventService.findEvent(con.getEvent_Id()),id));
        }

        return "redirect:/project/" + id;
    }
    @GetMapping("/project/{id}/edit")
    public String showProjectForm(@PathVariable("id") int id, Model model) {
        Project project = projectService.findProject(id);
        model.addAttribute("project", project);
        return "updateProject";
    }


    @PostMapping("project/{id}/update")
    public String updateProject(@PathVariable("id") int id, @Valid Project project,
                                BindingResult result, Model model) {

        project.setId_project(id);
        projectService.updateProject(project);
        return "redirect:/project/" + project.getId_project();
    }

    }