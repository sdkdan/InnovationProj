package ru.innovat.controller.major.project;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.service.major.ProjectService;
import ru.innovat.service.major.SearchService;


@Controller
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final SearchService searchService;

    @GetMapping(value = "/project")
    public String listProject(String search, Model model) {
        model.addAttribute("projectList", searchService.searchProjectList(search));
        return "project/project";
    }

    @GetMapping("/project/{id}/delete")
    public String deleteProject(@PathVariable("id") int id) {
        projectService.deleteProject(id);
        return "redirect:/project";
    }

    @GetMapping("project/{id}")
    public String oneProject(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.projectAllConnections(id));
        return "project/oneProject";
    }
}
