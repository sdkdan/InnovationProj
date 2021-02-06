package ru.innovat;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.support.Messages;
import ru.innovat.service.authorization.NewUserService;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class IntegrationTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private NewUserService newUserService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void integration() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        //checking access without login
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));

        mockMvc.perform(post("/register") //register new user
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "Ivan")
                .param("username", "vanya6222")
                .param("lastName", "Ivanov")
                .param("password", "1111")
                .param("passwordConfirm", "1111")
                .param("eMail", "stoletov9229225.dk@gmail.com")
                .sessionAttr("userForm", new AppUser())
        )
                .andExpect(status().isOk());

        //confirm email
        mockMvc.perform(get("/confirm-account?token=" + (newUserService.tokenList().get(newUserService
                .tokenList().size() - 1).toString())));

        //login
        mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "vanya6222")
                .param("password", "1111"));

        //send message to support
        mockMvc.perform(post("/help/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "Hello! how i can add a person to my project?")
                .sessionAttr("newMessage", new Messages())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/help"));
        mockMvc.perform(get("/help"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("messages", hasItem(
                        allOf(
                                hasProperty("userMessage", is(true)),
                                hasProperty("message", is("Hello! how i can add a person to" +
                                        " my project?"))
                        )
                )));

        //add new organization
        mockMvc.perform(post("/organization/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name_organization", "Политех")
                .param("site_organization", "spbbu@kek.ru")
                .param("notes_organization", "DFDFD")
                .param("city_organization", "Nursultan")
                .sessionAttr("organization", new Organization())
        )
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("organization/organization"))
                .andExpect(model().attribute("organizationList", hasSize(2)))
                .andExpect(model().attribute("organizationList", Matchers.hasItem(
                        allOf(
                                hasProperty("name_organization", is("Политех")),
                                hasProperty("site_organization", is("spbbu@gmail.ru")),
                                hasProperty("notes_organization", is("The best place in the world"))
                        )
                )));

        //delete organization
        mockMvc.perform(get("/organization/1/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());

        //change person
        mockMvc.perform(post("/person/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("surname", "Svetlakov")
                .param("name", "Sergey")
                .param("third_Name", "Ptun")
                .param("phone_number", "88005553666")
                .param("date_of_birth", "1997-22-02")
                .param("e_mail", "ssptun@gmail.com")
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
                        hasProperty("name", is("Sergey"))))
                .andExpect(model().attribute("person",
                        hasProperty("surname", is("Svetlakov"))));

        //add organization connection to person
        mockMvc.perform(post("/person/1/con")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("organization_Id", "1"));

        mockMvc.perform(get("/person/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("person/1"))
                .andExpect(model().attribute("org.name_organization", "Политех"));

        //exit from account
        mockMvc.perform(get("/logout"))
                .andExpect(status().is3xxRedirection());
    }

}
