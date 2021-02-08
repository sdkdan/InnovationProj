package ru.innovat.controller.major.organization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.OrganizationService;


@Controller
@RequiredArgsConstructor
public class AddOrganizationController {

    private final  OrganizationService organizationService;

    @GetMapping(value = "/organization/add")
    public String addOrganizationPage(Model model) {
        model.addAttribute("organization", new Organization());
        return "organization/addOrg";
    }

    @PostMapping(value = "/organization/add")
    public String addOrganization(@ModelAttribute Organization organization, Model model) {
        model.addAttribute("organization", organization);
        organizationService.addOrganization(organization);
        return "redirect:" + organization.getId_organization();
    }

}
