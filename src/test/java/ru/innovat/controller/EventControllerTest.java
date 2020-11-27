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
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.EventService;
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
@Sql(value = {"/sql/create-event-before.sql", "/sql/create-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "test")
public class EventControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    EventService eventService;
    @Autowired
    SearchService searchService;

    @Test
    public void events() throws Exception {
        this.mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("event/event"))
                .andExpect(model().attribute("eventList", hasSize(2)))
                .andExpect(model().attribute("eventList", hasItem(
                        allOf(
                                hasProperty("name_event", is("test")),
                                hasProperty("description", is("test")),
                                hasProperty("scope_event", is("test"))
                        )
                )));
    }

    @Test
    public void findByIdEventTest() throws Exception {
        mockMvc.perform(get("/event/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("event/oneEvent"))
                .andExpect(model().attribute("event",
                        hasProperty("name_event", is("test"))))
                .andExpect(model().attribute("event",
                        hasProperty("scope_event", is("test"))));
    }

    @Test
    public void eventSearchTest() throws Exception {
        mockMvc.perform(get("/event?search=test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("event/event"))
                .andExpect(model().attribute("eventList", hasSize(1)))
                .andExpect(model().attribute("eventList", hasItem(
                        allOf(
                                hasProperty("name_event", is("test")),
                                hasProperty("scope_event", is("test")),
                                hasProperty("description", is("test"))
                        )
                )));
    }

    @Test
    public void addNewEventTest() throws Exception {
        mockMvc.perform(post("/event/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("site_event", "testsite")
                .param("name_event", "test")
                .param("importance_event", "test")
                .param("scope_event", "test")
                .param("description", "test")
                .param("phone_number", "1111")
                .param("date_event", "28.12.1997")
                .param("date_for_month", "28.12.1997")
                .param("date_for_the_week", "28.12.1997")
                .param("comment", "test")
                .param("prizes", "test")
                .param("location_event", "test")
                .sessionAttr("event", new Event())
        )
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("event/event"))
                .andExpect(model().attribute("eventList", hasSize(2)))
                .andExpect(model().attribute("eventList", hasItem(
                        allOf(
                                hasProperty("name_event", is("test")),
                                hasProperty("site_event", is("test")),
                                hasProperty("description", is("test"))
                        )
                )));
    }

    @Test
    public void eventEditTest() throws Exception {
        mockMvc.perform(post("/event/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("site_event", "testsite")
                .param("name_event", "test1")
                .param("importance_event", "test")
                .param("scope_event", "test")
                .param("description", "test")
                .param("phone_number", "1111")
                .param("date_event", "28.12.1997")
                .param("date_for_month", "28.12.1997")
                .param("date_for_the_week", "28.12.1997")
                .param("comment", "test")
                .param("prizes", "test")
                .param("location_event", "test")
                .sessionAttr("project", new Project())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/event/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("event/oneEvent"))
                .andExpect(model().attribute("event",
                        hasProperty("name_event", is("test1"))))
                .andExpect(model().attribute("project",
                        hasProperty("site_event", is("testsite"))));
    }

    @Test
    public void deleteEventTest() throws Exception{
        mockMvc.perform(get("/event/1/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}