package ru.innovat.controller.major.person;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.innovat.models.major.Person;
import ru.innovat.service.major.PersonService;


import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class EditPersonController {

    private final PersonService personService;

    @GetMapping("/person/{id}/edit")
    public String showUpdateForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.findPerson(id));
        return "person/updatePerson";
    }

    @PostMapping("person/{id}/update")
    public String updateUser(@PathVariable("id") int id, @Valid Person person,
                             BindingResult result, @ModelAttribute String event) {
        person.setId_person(id);
        personService.updatePerson(person);
        return "redirect:";
    }
}
