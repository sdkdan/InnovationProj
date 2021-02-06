package ru.innovat.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Project;
import ru.innovat.search.EventSearch;
import ru.innovat.service.major.EventService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
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
    public void addNewEvent() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        int eventListSize = eventService.eventList().size();
        int newAddedEvent = 1;
        mockMvc.perform(post("/event/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("site_event", "https://www.spbstu.ru/")
                .param("name_event", "inovatproject")
                .param("importance_event", "Not important")
                .param("scope_event", "local")
                .param("description", "Test event")
                .param("phone_number", "88005553535")
                .param("date_event", "28.12.2020")
                .param("comment", "Test event")
                .param("prizes", "none")
                .param("location_event", "online")
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
                                hasProperty("name_event", is("inovatproject")),
                                hasProperty("site_event", is("https://www.spbstu.ru/")),
                                hasProperty("description", is("Test event"))
                        )
                )));
    }

    @Test
    public void getEventsList() throws Exception {
        List<Event> eventList = eventService.eventList();
        if (eventList.size() > 0) {
            this.mockMvc.perform(get("/event"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("event/event"))
                    .andExpect(model().attribute("eventList", hasSize(eventList.size())))
                    .andExpect(model().attribute("eventList", hasItem(
                            allOf(
                                    hasProperty("name_event", is(eventList.get(eventList.size() - 1)
                                            .getName_event())),
                                    hasProperty("description", is(eventList.get(eventList.size() - 1)
                                            .getDescription())),
                                    hasProperty("scope_event", is(eventList.get(eventList.size() - 1)
                                            .getScope_event()))
                            )
                    )));
        }
    }

    @Test
    public void findByIdEvent() throws Exception {
        List<Event> eventList = eventService.eventList();
        if (eventList.size() > 0) {
            int eventLastId = eventList.get(eventList.size() - 1).getId_event();
            mockMvc.perform(get("/event/{id}", eventLastId)).andExpect(status().isOk())
                    .andExpect(view().name("event/oneEvent"))
                    .andExpect(model().attribute("event",
                            allOf(
                                    hasProperty("name_event", is(eventList.get(eventList.size() - 1)
                                            .getName_event())),
                                    hasProperty("description", is(eventList.get(eventList.size() - 1)
                                            .getDescription())),
                                    hasProperty("scope_event", is(eventList.get(eventList.size() - 1)
                                            .getScope_event()))
                            )
                    ));
        }
    }

    @Test
    public void eventSearch() throws Exception {
        if (eventService.eventList().size() > 0) {
            Event lastEvent = eventService.eventList().get(eventService.eventList().size() - 1);
            String lastEventName = lastEvent.getName_event();
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
                                    hasProperty("name_event", is(lastFoundedEvent.getName_event())),
                                    hasProperty("scope_event", is(lastFoundedEvent.getScope_event())),
                                    hasProperty("description", is(lastFoundedEvent.getDescription()))
                            ))));
        }
    }

    @Test
    public void eventEdit() throws Exception {
        List<Event> eventList = eventService.eventList();
        if (eventList.size() > 0) {
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
    }

    @Test
    public void deleteEvent() throws Exception {
        List<Event> eventList = eventService.eventList();
        if (eventList.size() > 0) {
            int eventLastId = eventList.get(eventList.size() - 1).getId_event();
            mockMvc.perform(get("/event/{id}/delete", eventLastId))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        }
    }
}