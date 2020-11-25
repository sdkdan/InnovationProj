package ru.innovat.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.innovat.controller.major.event.EventController;
import ru.innovat.models.major.Event;
import ru.innovat.service.major.EventService;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@WithUserDetails("test")
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-event-before.sql","/sql/create-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EventControllerTest {
    @Autowired
    EventController eventController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    EventService eventService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Мероприятия")));
    }

    @Test
    public void eventPageTest() throws Exception {
        this.mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void eventListTest() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        this.mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/table/tbody/tr[1]/td[1]").string("Лул"));
    }

    @Test
    public void addNewEventTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/event/add")
                .param("event", String.valueOf(new Event()))
                .contentType(MediaType.TEXT_HTML)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteEvent() throws Exception {
        String uri = "/event/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Event is deleted successfully");
    }

}
