package ru.innovat.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.innovat.models.support.Messages;
import ru.innovat.service.authorization.UserService;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-user-for-chat.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ChatControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "SUPPORT")
    public void checkAccessWithSupportRole() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/support"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void checkAccessWithoutLogin() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/support"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));;
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void checkAccessWithRoleUser() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/support"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void checkAccessWithUserRoleToUserChat() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/help"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void checkAccessWithoutLoginToUserChat() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/help"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "SUPPORT")
    public void checkAccessWithSupportRoleToUserChat() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/help"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "test1", password = "pwd", roles = "USER")
    public void checkSendingMessageFromUser() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(post("/help/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "test")
                .sessionAttr("newMessage", new Messages())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/help"));
        mockMvc.perform(get("/help"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("messages", hasItem(
                        allOf(
                                hasProperty("userMessage", is(true)),
                                hasProperty("message", is("test"))
                        )
                )));
    }

    @Test
    @WithMockUser(username = "test2", password = "pwd", roles = "SUPPORT")
    public void checkSendingMessageFromSupport() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(post("/support/2/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "test")
                .param("idUser", "2")
                .sessionAttr("newMessage", new Messages())
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"));
        mockMvc.perform(get("/support/2"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(model().attribute("messages", hasItem(
                        allOf(
                                hasProperty("userMessage", is(false)),
                                hasProperty("message", is("test"))
                        )
                )));
    }

}
