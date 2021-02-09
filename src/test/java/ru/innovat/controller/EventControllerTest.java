package ru.innovat.controller;

import org.junit.After;
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

    @Before
    public void createTestEvent() {
        Event event1 = Event.builder()
                            .nameEvent("Хакатон")
                            .importanceEvent("важно")
                            .description("Только для новых разработчиков")
                            .dateEvent("2021:02:28")
                            .prizes("бонус на собеседовании")
                            .siteEvent("hackaton.ru")
                            .idTypeEvent(1)
                            .build();
        Event event2 = Event.builder()
                            .nameEvent("Доклады по новым технологиям")
                            .importanceEvent("важно")
                            .description("Вход своодный")
                            .dateEvent("2021:02:28")
                            .prizes("мелкие призы от компаний")
                            .siteEvent("event.ru")
                .idTypeEvent(1)
                .build();

        eventService.addEvent(event1);
        eventService.addEvent(event2);
    }

    @After
    public void deleteEvents() {
        List<Event> eventList = eventService.eventList();
        for (Event event: eventList
             ) {
            eventService.deleteEvent(event.getId_event());
        }
    }

    @Test
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
                .param("siteEvent", "https://www.spbstu.ru/")
                .param("nameEvent", "inovations projects")
                .param("importanceEvent", "Not important")
                .param("scopeEvent", "local")
                .param("description", "Test event")
                .param("phoneNumber", "88005553535")
                .param("dateEvent", "28.12.2020")
                .param("comment", "Test event")
                .param("prizes", "none")
                .param("locationEvent", "online")
                .param("idTypeEvent", "1")
                .sessionAttr("project", new Project())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/event/{id}", eventLastId))
                .andExpect(status().isOk())
                .andExpect(view().name("event/oneEvent"))
                .andExpect(model().attribute("event",
                        allOf(
                                hasProperty("nameEvent", is("inovations projects")),
                                hasProperty("description", is("Test event")),
                                hasProperty("scopeEvent", is("local"))
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