
package ru.innovat.controller.major.person;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.innovat.search.PersonSearch;
import ru.innovat.service.major.PersonService;

@Controller
@AllArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final PersonSearch searchService;

    @GetMapping(value = "/person")
    public String listPerson(String search, Model model) {
        model.addAttribute("personList", searchService.fuzzySearch(search));
        return "person/person";
    }

    @GetMapping("person/{id}/delete")
    public String deleteUser(@PathVariable("id") int id) {
        personService.deletePerson(id);
        return "redirect:/person";
    }

    @GetMapping("person/{id}")
    public String onePerson(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personService.personAllConnections(id));
        return "person/onePerson";
    }
}
