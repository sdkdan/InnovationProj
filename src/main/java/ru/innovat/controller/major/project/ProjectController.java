package ru.innovat.controller.major.project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.search.ProjectSearch;
import ru.innovat.service.major.ProjectService;


@Controller
@AllArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectSearch searchService;

    @GetMapping(value = "/project")
    public String listProject(String search, Model model) {
        model.addAttribute("projectList", searchService.fuzzySearch(search));
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
