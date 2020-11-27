package ru.innovat.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.innovat.controller.major.organization.OrganizationController;
import ru.innovat.models.major.Person;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.SearchService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-person-before.sql", "/sql/create-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "test")
public class PersonControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrganizationController organizationController;
    @Autowired
    PersonService personService;
    @Autowired
    SearchService searchService;

    @Test
    public void persons() throws Exception {
        this.mockMvc.perform(get("/person"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("person/person"))
                .andExpect(model().attribute("personList", hasSize(2)))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("name", is("Марцинкевич")),
                                hasProperty("surname", is("Максим")),
                                hasProperty("date_of_birth", is("1997-28-02"))
                        )
                )));
    }

    @Test
    public void findByIdPersonTest() throws Exception {
        mockMvc.perform(get("/person/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("person/onePerson"))
                .andExpect(model().attribute("person",
                        hasProperty("name", is("Марцинкевич"))))
                .andExpect(model().attribute("person",
                        hasProperty("surname", is("Максим"))));
    }

    @Test
    public void personSearchTest() throws Exception {
        mockMvc.perform(get("/person?search=Марцинкевич"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("person/person"))
                .andExpect(model().attribute("personList", hasSize(1)))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("name", is("Марцинкевич")),
                                hasProperty("surname", is("Максим")),
                                hasProperty("date_of_birth", is("1997-28-02"))
                        )
                )));
    }

    @Test
    public void addNewPersonTest() throws Exception {
        mockMvc.perform(post("/person/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("surname", "Максим")
                .param("name", "Марцинкевич")
                .param("third_Name", "Абрамович")
                .param("phone_number", "88005553535")
                .param("date_of_birth", "1997-28-02")
                .param("e_mail", "SlavaUkraine@geroyam.slava")
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
                .andExpect(model().attribute("personList", hasSize(3)))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("name", is("Марцинкевич")),
                                hasProperty("surname", is("Максим")),
                                hasProperty("e_mail", is("SlavaUkraine@geroyam.slava"))
                        )
                )));
    }

    @Test
    public void personEditTest() throws Exception {
        mockMvc.perform(post("/person/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("surname", "МарцинкевичTest")
                .param("name", "Максим")
                .param("third_Name", "Абрамович")
                .param("phone_number", "88005553666")
                .param("date_of_birth", "1997-28-02")
                .param("e_mail", "SlavaUkraine@geroyam.slava")
                .param("facebook", "Фейсбук")
                .param("vk", "ВК")
                .param("rating", "10")
                .param("twitter", "Твитер")
                .param("comment", "комментарий")
                .sessionAttr("person", new Person())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/person/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("person/onePerson"))
                .andExpect(model().attribute("person",
                        hasProperty("name", is("Максим"))))
                .andExpect(model().attribute("person",
                        hasProperty("surname", is("МарцинкевичTest"))));
    }

    @Test
    public void deletePersonTest() throws Exception{
        mockMvc.perform(get("/person/1/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}