package ru.innovat.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.innovat.controller.major.organization.OrganizationController;
import ru.innovat.models.major.Person;
import ru.innovat.search.PersonSearch;
import ru.innovat.service.major.PersonService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WithMockUser(username = "test", password = "pwd", roles = "ADMIN")
public class PersonControllerTest extends ConfigControllerTest {

    @Autowired
    OrganizationController organizationController;

    @Autowired
    PersonService personService;

    @Autowired
    PersonSearch personSearch;

    @Before
    public void createPetsonForTests() {
        Person newPerson1 = Person.builder()
                .surname("Мурсаев")
                .name("Иван")
                .thirdName("Владимирович")
                .build();
        Person newPerson2 = Person.builder()
                                  .surname("Иванов")
                                  .name("Олег")
                                  .thirdName("Игоревич")
                                  .build();

        personService.addPerson(newPerson1);
        personService.addPerson(newPerson2);
    }

    @After
    public void deletePerson(){
        for(Person person: personService.personList()){
            personService.deletePerson(person.getId_person());
        }
    }
    @Test
    public void personSearch() throws Exception {
        Person lastPerson = personService.personList().get(personService
                .personList().size() - 1);
        String lastPersonName = lastPerson.getName();
        int foundedPersonsListSize = personSearch.fuzzySearch(lastPersonName).size();
        Person lastFoundedPerson = personSearch.fuzzySearch(lastPersonName).get(personSearch
                .fuzzySearch(lastPersonName).size() - 1);
        mockMvc.perform(get("/person?search=" + lastPersonName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("person/person"))
                .andExpect(model().attribute("personList", hasSize(foundedPersonsListSize)))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("name", is(lastFoundedPerson.getName())),
                                hasProperty("surname", is(lastFoundedPerson.getSurname())),
                                hasProperty("dateOfBirth", is(lastFoundedPerson.getDateOfBirth()))
                        )
                )));
    }


    @Test
    public void addNewPersonTest() throws Exception {
        int personListSize = personService.personList().size();
        int newAddedPerson = 1;
        mockMvc.perform(post("/person/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("surname", "Максим")
                .param("name", "Марцинкевич")
                .param("thirdName", "Абрамович")
                .param("phoneNumber", "88005553535")
                .param("dateOfBirth", "1997-28-02")
                .param("eMail", "maksim99@gmail.com")
                .param("facebook", "Фейсбук")
                .param("vk", "ВК")
                .param("rating", "10")
                .param("twitter", "Твитер")
                .param("comment", "комментарий")
                .sessionAttr("person", new Person())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("person/person"))
                .andExpect(model().attribute("personList", hasSize(personListSize + newAddedPerson)))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("name", is("Марцинкевич")),
                                hasProperty("surname", is("Максим"))
                        )
                )));
    }

    @Test
    public void personEditTest() throws Exception {
        List<Person> personList = personService.personList();
        int lastIdPerson = personList.get(personList.size() - 1).getId_person();
        mockMvc.perform(post("/person/{id}/update", lastIdPerson)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("surname", "Бугров")
                .param("name", "Алекс")
                .param("thirdName", "Абрамович")
                .param("phoneNumber", "88005553535")
                .param("dateOfBirth", "1997-28-02")
                .param("eMail", "maksim99@gmail.com")
                .param("facebook", "Фейсбук")
                .param("vk", "ВК")
                .param("rating", "10")
                .param("twitter", "Твитер")
                .param("comment", "комментарий")
                .sessionAttr("person", new Person())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/person/{id}", lastIdPerson))
                .andExpect(status().isOk())
                .andExpect(view().name("person/onePerson"))
                .andExpect(model().attribute("person",
                        allOf(
                                hasProperty("name", is("Алекс")),
                                hasProperty("surname", is("Бугров"))
                        )));
    }

    @Test
    public void deletePersonTest() throws Exception {
        List<Person> personList = personService.personList();
        int personLastId = personList.get(personList.size() - 1).getId_person();
        mockMvc.perform(get("/person/{id}/delete", personLastId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
