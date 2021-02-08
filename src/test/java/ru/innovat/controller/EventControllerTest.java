package ru.innovat.controller;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Project;
import ru.innovat.search.EventSearch;
import ru.innovat.service.major.EventService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WithMockUser(username = "test", password = "pwd", roles = "ADMIN")
public class EventControllerTest extends ConfigControllerTest {

    @Autowired
    EventService eventService;
    @Autowired
    EventSearch eventSearch;

    @Test
    @Before
    public void addNewEventTest() throws Exception {
        int eventListSize = eventService.eventList().size();
        int newAddedEvent = 1;
        mockMvc.perform(post("/event/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("siteEvent", "https://www.spbstu.ru/")
                .param("nameEvent", "inovatproject")
                .param("importanceEvent", "Not important")
                .param("scopeEvent", "local")
                .param("description", "Test event")
                .param("phoneNumber", "88005553535")
                .param("dateEvent", "28.12.2020")
                .param("comment", "Test event")
                .param("prizes", "none")
                .param("locationEvent", "online")
                .param("idTypeEvent", "1")
                .sessionAttr("event", new Event())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/event"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("event/event"))
                .andExpect(model().attribute("eventList", hasSize(eventListSize + newAddedEvent)))
                .andExpect(model().attribute("eventList", hasItem(
                        allOf(
                                hasProperty("nameEvent", is("inovatproject")),
                                hasProperty("siteEvent", is("https://www.spbstu.ru/")),
                                hasProperty("description", is("Test event"))
                        )
                )));
    }

    @Test
    public void eventSearch() throws Exception {
        Event lastEvent = eventService.eventList().get(eventService.eventList().size() - 1);
        String lastEventName = lastEvent.getNameEvent();
        int foundedEventsListSize = eventSearch.fuzzySearch(lastEventName).size();
        Event lastFoundedEvent = eventSearch.fuzzySearch(lastEventName).get(eventSearch.fuzzySearch(lastEventName)
                .size() - 1);
        mockMvc.perform(get("/event?search=" + lastEventName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("event/event"))
                .andExpect(model().attribute("eventList", hasSize(foundedEventsListSize)))
                .andExpect(model().attribute("eventList", hasItem(
                        allOf(
                                hasProperty("nameEvent", is(lastFoundedEvent.getNameEvent())),
                                hasProperty("scopeEvent", is(lastFoundedEvent.getScopeEvent())),
                                hasProperty("description", is(lastFoundedEvent.getDescription()))
                        ))));
    }

    @Test
    public void eventEditTest() throws Exception {
        List<Event> eventList = eventService.eventList();
        int eventLastId = eventList.get(eventList.size() - 1).getId_event();
        mockMvc.perform(post("/event/{id}/update", eventLastId)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("site_event", "https://www.spbstu.ru/")
                .param("name_event", "inovations projects")
                .param("importance_event", "Not important")
                .param("scope_event", "local")
                .param("description", "Test event")
                .param("phone_number", "88005553535")
                .param("date_event", "28.12.2020")
                .param("comment", "Test event")
                .param("prizes", "none")
                .param("location_event", "online")
                .param("idTypeEvent", "1")
                .sessionAttr("project", new Project())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/event/{id}", eventLastId))
                .andExpect(status().isOk())
                .andExpect(view().name("event/oneEvent"))
                .andExpect(model().attribute("event",
                        allOf(
                                hasProperty("name_event", is("inovations projects")),
                                hasProperty("description", is("Test event")),
                                hasProperty("scope_event", is("local"))
                        )));
    }

    @Test
    public void deleteEventTest() throws Exception {
        List<Event> eventList = eventService.eventList();
        int eventLastId = eventList.get(eventList.size() - 1).getId_event();
        mockMvc.perform(get("/event/{id}/delete", eventLastId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}