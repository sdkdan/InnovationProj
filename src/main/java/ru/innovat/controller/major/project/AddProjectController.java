package ru.innovat.controller.major.project;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.ProjectService;


@Controller
@AllArgsConstructor
public class AddProjectController {
    private final ProjectService projectService;

    @GetMapping(value = "/project/add")
    public String getAddProject(Model model) {

        model.addAttribute("project", new Project());
        return "project/addproject";
    }

    @PostMapping(value = "/project/add")
    public String addProject(@ModelAttribute Project project, Model model) {
        model.addAttribute("project", project);
        projectService.addProject(project);
        return "redirect:" + project.getId_project();
    }
}
