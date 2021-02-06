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
@Sql(value = {"/sql/create-event-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource("/application-test.properties")
@Transactional
public class EventServiceWithBDTest {
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
        assertThat(event.getSiteEvent()).isEqualTo("testsite");
        assertThat(event.getNameEvent()).isEqualTo("test");
        assertThat(event.getImportanceEvent()).isEqualTo("test");
        assertThat(event.getScopeEvent()).isEqualTo("test");
        assertThat(event.getDescription()).isEqualTo("test");
        assertThat(event.getPhoneNumber()).isEqualTo("1111");
        assertThat(event.getDateEvent()).isEqualTo("28.12.1997");
        assertThat(event.getComment()).isEqualTo("test");
        assertThat(event.getPrizes()).isEqualTo("test");
    }

    @Test
    public void eventListTest(){
        List<Event> eventList = eventService.eventList();
        assertThat(eventList.get(0).getId_event()).isEqualTo(1);
        assertThat(eventList.get(0).getSiteEvent()).isEqualTo("testsite");
        assertThat(eventList.get(0).getNameEvent()).isEqualTo("test");
        assertThat(eventList.get(0).getImportanceEvent()).isEqualTo("test");
        assertThat(eventList.get(0).getScopeEvent()).isEqualTo("test");
        assertThat(eventList.get(0).getDescription()).isEqualTo("test");
        assertThat(eventList.get(0).getPhoneNumber()).isEqualTo("1111");
        assertThat(eventList.get(0).getDateEvent()).isEqualTo("28.12.1997");
        assertThat(eventList.get(0).getComment()).isEqualTo("test");
        assertThat(eventList.get(0).getPrizes()).isEqualTo("test");
        assertThat(eventList.get(1).getId_event()).isEqualTo(1);
        assertThat(eventList.get(1).getSiteEvent()).isEqualTo("testsite");
        assertThat(eventList.get(1).getNameEvent()).isEqualTo("test2");
        assertThat(eventList.get(1).getImportanceEvent()).isEqualTo("test");
        assertThat(eventList.get(1).getScopeEvent()).isEqualTo("test");
        assertThat(eventList.get(1).getDescription()).isEqualTo("test");
        assertThat(eventList.get(1).getPhoneNumber()).isEqualTo("1111");
        assertThat(eventList.get(1).getDateEvent()).isEqualTo("28.12.1997");
        assertThat(eventList.get(1).getComment()).isEqualTo("test");
        assertThat(eventList.get(1).getPrizes()).isEqualTo("test");

    }

    @Test
    public void updateEventTest(){
        Event event = eventService.findEvent(1);
        event.setNameEvent("test1");
        event.setComment("testComment");
        eventService.updateEvent(event);
        Event updatedEvent = eventService.findEvent(1);
        assertThat(event.getId_event()).isEqualTo(updatedEvent.getId_event());
        assertThat(event.getSiteEvent()).isEqualTo(updatedEvent.getSiteEvent());
        assertThat(event.getNameEvent()).isEqualTo(updatedEvent.getNameEvent());
        assertThat(event.getImportanceEvent()).isEqualTo(updatedEvent.getImportanceEvent());
        assertThat(event.getScopeEvent()).isEqualTo(updatedEvent.getScopeEvent());
        assertThat(event.getDescription()).isEqualTo(updatedEvent.getDescription());
        assertThat(event.getPhoneNumber()).isEqualTo(updatedEvent.getPhoneNumber());
        assertThat(event.getDateEvent()).isEqualTo(updatedEvent.getDateEvent());
        assertThat(event.getComment()).isEqualTo(updatedEvent.getComment());
        assertThat(event.getPrizes()).isEqualTo(updatedEvent.getPrizes());
    }

}
