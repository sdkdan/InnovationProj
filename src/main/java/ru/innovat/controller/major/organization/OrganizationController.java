package ru.innovat.controller.major.organization;

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
import ru.innovat.search.OrganizationSearch;
import ru.innovat.service.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;
    private final SearchService searchService;

    @GetMapping(value = "/organization")
    public String listorganization(String search, Model model) {
        model.addAttribute("organizationList", searchService.searchListOrganization(search));
        return "organization/organization";
    }

    @GetMapping("organization/{id}/delete")
    public String deleteOrganization(@PathVariable("id") int id) {
        organizationService.deleteOrganiztion(id);
        return "redirect:organization";
    }

    @GetMapping("organization/{id}")
    public String oneOrganization(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.organizationAllConnection(id);
        model.addAttribute("organization", organization);
        return "organization/oneOrg";
    }

}