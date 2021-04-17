package ru.innovat.controller.major.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.ProjectService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EditProjectController {

    private final ProjectService projectService;

    @GetMapping("/project/{id}/edit")
    public String showProjectForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("project", projectService.findProject(id));
        return "project/updateProject";
    }

    @PostMapping("project/{id}/update")
    public String updateProject(@PathVariable("id") int id, @Valid Project project, BindingResult bindingResult) {
        project.setId_project(id);
        projectService.updateProject(project);
        return "redirect:";
    }
}
