package ru.innovat.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.innovat.service.major.SearchService;

@Controller
@AllArgsConstructor
public class MainModelPages {
    SearchService searchService;

    @GetMapping(value = "/organization")
    public String listorganization(String search, Model model) {
        model.addAttribute("organizationList", searchService.searchListOrganization(search));
        return "organization/organization";
    }

    @GetMapping(value = "/person")
    public String listPerson(String search, Model model) {
        model.addAttribute("personList", searchService.searchPersonList(search));
        return "person/person";
    }

    @GetMapping(value = "/project")
    public String listProject(String search, Model model) {
        model.addAttribute("projectList", searchService.searchProjectList(search));
        return "project/project";
    }

    @GetMapping(value = "/event")
    public String listEvent(String search, Model model) {
        model.addAttribute("eventList", searchService.searchEventList(search));
        return "event/event";
    }
}
