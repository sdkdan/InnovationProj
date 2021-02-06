package ru.innovat.controller.major.organization;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.SearchService;


@Controller
@RequiredArgsConstructor
public class OrganizationController {
    private final OrganizationService organizationService;
    private final SearchService searchService;

    @GetMapping(value = "/organization")
    public String listOrganization(String search, Model model) {
        model.addAttribute("organizationList", searchService.searchListOrganization(search));
        return "organization/organization";
    }

    @GetMapping("organization/{id}/delete")
    public String deleteOrganization(@PathVariable("id") int id) {
        organizationService.deleteOrganization(id);
        return "redirect:organization";
    }

    @GetMapping("organization/{id}")
    public String oneOrganization(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.organizationAllConnection(id);
        model.addAttribute("organization", organization);
        return "organization/oneOrg";
    }
}