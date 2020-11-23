package ru.innovat.controller.major.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.Person;
import ru.innovat.service.PersonService;


@Controller
@AllArgsConstructor
public class AddPersonController {
    private final PersonService personService;

    @GetMapping(value = "/person/add")
    public String getAddPerson(Model model) {
        model.addAttribute("person", new Person());
        return "person/add";
    }

    @PostMapping(value = "/person/add")
    public String addPerson(@ModelAttribute Person person, Model model) {
        model.addAttribute("person", person);
        personService.addPerson(person);
        return "redirect:" + person.getId_person();
    }
}
