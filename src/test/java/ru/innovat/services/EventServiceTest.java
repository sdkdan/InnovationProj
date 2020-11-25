package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"create-event-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource("../../../../resources/application-test.properties")
@Transactional
public class EventServiceTest {
    @Autowired
    ProjectService projectService;
    @Autowired
    EventService eventService;
    @Autowired
    PersonService personService;
    @Autowired
    OrganizationService organizationService;


    @Test
    public void testFindByIdWithBD() {
        Event event = eventService.findEvent(1);
        assertThat(event.getId_event()).isEqualTo(1);
        assertThat(event.getSite_event()).isEqualTo("testsite");
        assertThat(event.getName_event()).isEqualTo("test");
        assertThat(event.getImportance_event()).isEqualTo("test");
        assertThat(event.getScope_event()).isEqualTo("test");
        assertThat(event.getDescription()).isEqualTo("test");
        assertThat(event.getPhone_number()).isEqualTo("1111");
        assertThat(event.getDate_event()).isEqualTo("28.12.1997");
        assertThat(event.getComment()).isEqualTo("test");
        assertThat(event.getPrizes()).isEqualTo("test");
    }

    @Test
    public void eventListTest(){
        List<Event> eventList = eventService.eventList();
        assertThat(eventList.get(0).getId_event()).isEqualTo(1);
        assertThat(eventList.get(0).getSite_event()).isEqualTo("testsite");
        assertThat(eventList.get(0).getName_event()).isEqualTo("test");
        assertThat(eventList.get(0).getImportance_event()).isEqualTo("test");
        assertThat(eventList.get(0).getScope_event()).isEqualTo("test");
        assertThat(eventList.get(0).getDescription()).isEqualTo("test");
        assertThat(eventList.get(0).getPhone_number()).isEqualTo("1111");
        assertThat(eventList.get(0).getDate_event()).isEqualTo("28.12.1997");
        assertThat(eventList.get(0).getComment()).isEqualTo("test");
        assertThat(eventList.get(0).getPrizes()).isEqualTo("test");
        assertThat(eventList.get(1).getId_event()).isEqualTo(1);
        assertThat(eventList.get(1).getSite_event()).isEqualTo("testsite");
        assertThat(eventList.get(1).getName_event()).isEqualTo("test2");
        assertThat(eventList.get(1).getImportance_event()).isEqualTo("test");
        assertThat(eventList.get(1).getScope_event()).isEqualTo("test");
        assertThat(eventList.get(1).getDescription()).isEqualTo("test");
        assertThat(eventList.get(1).getPhone_number()).isEqualTo("1111");
        assertThat(eventList.get(1).getDate_event()).isEqualTo("28.12.1997");
        assertThat(eventList.get(1).getComment()).isEqualTo("test");
        assertThat(eventList.get(1).getPrizes()).isEqualTo("test");

    }

    @Test
    public void updateEventTest(){
        Event event = eventService.findEvent(1);
        event.setName_event("test1");
        event.setComment("testComment");
        eventService.updateEvent(event);
        Event updatedEvent = eventService.findEvent(1);
        assertThat(event.getId_event()).isEqualTo(updatedEvent.getId_event());
        assertThat(event.getSite_event()).isEqualTo(updatedEvent.getSite_event());
        assertThat(event.getName_event()).isEqualTo(updatedEvent.getName_event());
        assertThat(event.getImportance_event()).isEqualTo(updatedEvent.getImportance_event());
        assertThat(event.getScope_event()).isEqualTo(updatedEvent.getScope_event());
        assertThat(event.getDescription()).isEqualTo(updatedEvent.getDescription());
        assertThat(event.getPhone_number()).isEqualTo(updatedEvent.getPhone_number());
        assertThat(event.getDate_event()).isEqualTo(updatedEvent.getDate_event());
        assertThat(event.getComment()).isEqualTo(updatedEvent.getComment());
        assertThat(event.getPrizes()).isEqualTo(updatedEvent.getPrizes());
    }

//    @Test
//    public void  addEventTest(){
//        Event event = new Event();
//        event.setSite_event("testsite");
//        event.setName_event("test");
//        event.setImportance_event("test");
//        event.setScope_event("test");
//        event.setDescription("test");
//        event.setPhone_number("1111");
//        event.setDate_event("28.12.1997");
//        event.setComment("test");
//        event.setPrizes("test");
//        eventService.addEvent(event);
//        verify(eventService,times(1)).addEvent(event);
//        Event addedEvent = eventService.findEvent(15);
//        assertThat(event.getId_event()).isEqualTo(addedEvent.getId_event());
//        assertThat(event.getSite_event()).isEqualTo(addedEvent.getSite_event());
//        assertThat(event.getName_event()).isEqualTo(addedEvent.getName_event());
//        assertThat(event.getImportance_event()).isEqualTo(addedEvent.getImportance_event());
//        assertThat(event.getScope_event()).isEqualTo(addedEvent.getScope_event());
//        assertThat(event.getDescription()).isEqualTo(addedEvent.getDescription());
//        assertThat(event.getPhone_number()).isEqualTo(addedEvent.getPhone_number());
//        assertThat(event.getDate_event()).isEqualTo(addedEvent.getDate_event());
//        assertThat(event.getComment()).isEqualTo(addedEvent.getComment());
//        assertThat(event.getPrizes()).isEqualTo(addedEvent.getPrizes());
//    }
}