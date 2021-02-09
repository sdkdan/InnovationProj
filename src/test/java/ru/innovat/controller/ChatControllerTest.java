package ru.innovat.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innovat.models.support.Messages;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ChatControllerTest extends ConfigControllerTest {

    @Test
    @WithMockUser(username = "test1", password = "pwd", roles = "USER")
    public void sendingMessage_FromUser() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(post("/help/send")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("message", "test")
                .sessionAttr("newMessage", new Messages())
        )
                .andExpect(status().is3xxRedirection());

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
    public void sendingMessage_FromSupport() throws Exception {
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
