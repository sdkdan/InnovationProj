package ru.innovat.controller.major.organization;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.OrganizationService;


import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class EditOrganizationController {
    private final OrganizationService organizationService;

    @GetMapping("/organization/{id}/edit")
    public String showOrganizationForm(@PathVariable("id") int id, Model model) {
        Organization organization = organizationService.findOrganization(id);
        model.addAttribute("organization", organization);
        return "organization/updateOrganization";
    }

    @PostMapping("organization/{id}/update")
    public String updateOrganization(@PathVariable("id") int id, @ModelAttribute Organization organization, BindingResult bindingResult) {
        organization.setId_organization(id);
        organizationService.updateOrganization(organization);
        return "redirect:";
    }
}
