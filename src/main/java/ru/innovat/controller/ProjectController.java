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
import ru.innovat.search.ProjectSearch;
import ru.innovat.service.EventService;
import ru.innovat.service.OrganizationService;
import ru.innovat.service.PersonService;
import ru.innovat.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class ProjectController {
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventService eventService;
    private final OrganizationService organizationService;
    private final ProjectSearch projectSearch;


    @RequestMapping(value = "/project")
    public String listProject(String search, Model model) {
        model.addAttribute("projectList", projectSearch.searchProjectList(search));
        return "project/project";
    }

    @GetMapping("/project/{id}/delete")
    public String deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return "redirect:/project";
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.GET)
    public String getAddProject(Model model) {

        model.addAttribute("project", new Project());
        return "project/addproject";
    }

    @RequestMapping(value = "/project/add", method = RequestMethod.POST)
    public String addProject(@ModelAttribute Project project, Model model) {
        model.addAttribute("project", project);
        projectService.addProject(project);
        return "redirect:" + project.getId_project();
    }


    @GetMapping("project/{id}")
    public String oneProject(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.projectAllConnections(id));

        return "project/oneProject";
    }

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

    @RequestMapping(value = "/project/{id}/con", method = RequestMethod.POST)
    public String eventAddCon(@PathVariable("id") int id, @ModelAttribute Project project, Connect con, Model model){
        model.addAttribute("project", project);
        model.addAttribute("con", con);
        projectService.addConnections(con,id);
        return "redirect:";
    }

    @GetMapping("/project/{id}/edit")
    public String showProjectForm(@PathVariable("id") int id, Model model) {
        Project project = projectService.findProject(id);
        model.addAttribute("project", project);
        return "project/updateProject";
    }


    @PostMapping("project/{id}/update")
    public String updateProject(@PathVariable("id") int id, @Valid Project project, BindingResult bindingResult) {

        project.setId_project(id);
        projectService.updateProject(project);
        return "redirect:";
    }

}
