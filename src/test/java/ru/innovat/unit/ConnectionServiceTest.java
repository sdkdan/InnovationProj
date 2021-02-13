package ru.innovat.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.*;


import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Transactional
public class ConnectionServiceTest {

    @Autowired
    ConnectionService connectService;

    @Autowired
    ProjectService projectService;

    @Autowired
    EventService eventService;

    @Autowired
    PersonService personService;

    @Autowired
    OrganizationService organizationService;

    @Test
    public void newConnection_AddToProject() {
        int organizationId = 1;
        int eventId = 1;
        int personId = 1;
        int projectId = 1;
        Connect connect = Connect.builder()
                                 .organization_Id(organizationId)
                                 .event_Id(eventId)
                                 .person_id(personId)
                                 .build();

        connectService.addConnections(connect, projectService.findProject(projectId));

        Event event = eventService.findEvent(eventId);
        Person person = personService.findPerson(personId);
        Organization organization = organizationService.findOrganization(organizationId);

        Set<Event> eventSet = new HashSet<>();
        Set<Person> personSet = new HashSet<>();
        Set<Organization> organizationSet = new HashSet<>();
        eventSet.add(event);
        personSet.add(person);
        organizationSet.add(organization);

        assertThat(projectService.findProject(projectId).getEvents()).isEqualTo(eventSet);
        assertThat(projectService.findProject(projectId).getPersons()).isEqualTo(personSet);
        assertThat(projectService.findProject(projectId).getOrganizations()).isEqualTo(organizationSet);
    }

}
