package ru.innovat.controller.major.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Person;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.ConnectionService;
import ru.innovat.service.major.PersonService;

@Controller
@RequiredArgsConstructor
public class ConnectionPersonController {

    private final PersonService personService;
    private final ConnectionService connectionService;

    @GetMapping("person/{id}/connect")
    public String personAddConnectionPage(@PathVariable("id") int id, Model model) {
        Person person = personService.personAllConnections(id);
        model.addAttribute("list", connectionService.removeConnectionsFormEventList(person.getEvents()));
        model.addAttribute("projectlist", connectionService.removeConnectionsFromProjectList(person.getProjects()));
        model.addAttribute("orglist", connectionService.removeConnectionsFormOrganizationList(person.getOrganizations()));
        model.addAttribute("person", person);
        model.addAttribute("personcon", new Connect());
        return "person/addPersonCon";
    }

    @PostMapping(value = "/person/{id}/connect")
    public String personAddConnection(@PathVariable("id") int id, @ModelAttribute Person person, Connect connect) {
        connectionService.addConnections(connect, personService.personAllConnections(id));
        return "redirect:";
    }
}
