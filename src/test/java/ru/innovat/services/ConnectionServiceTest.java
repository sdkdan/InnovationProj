package ru.innovat.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.ConnectionService;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


public class ConnectionServiceTest extends ConfigServiceTest {
    @Autowired
    ConnectionService connectService;

    @Test
    public void newConnection_AddToProject() {//addToProject_Connection_addedConnection Second name of test
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
