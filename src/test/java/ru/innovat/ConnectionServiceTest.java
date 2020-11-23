package ru.innovat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("application-test.properties")
@Sql(value = {"create-project-before.sql"})
@Transactional
public class ConnectionServiceTest {
    @Autowired
    ProjectService projectService;
    @Autowired
    EventService eventService;
    @Autowired
    PersonService personService;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    ConnectionService connectService;


    @Test
    public void addConnectionToProjectTest(){
        Connect connect = new Connect();
        connect.setOrganization_Id(1);
        connect.setEvent_Id(1);
        connect.setPerson_id(1);
        connectService.addConnections(connect,projectService.findProject(1));
        Event event = eventService.findEvent(1);
        Person person = personService.findPerson(1);
        Organization organization = organizationService.findOrganization(1);
        Set<Event> eventSet = new HashSet<>();
        Set<Person> personSet = new HashSet<>();
        Set<Organization> organizationSet = new HashSet<>();
        eventSet.add(event);
        personSet.add(person);
        organizationSet.add(organization);
        assertThat(projectService.findProject(1).getEvents()).isEqualTo(eventSet);
        assertThat(projectService.findProject(1).getPersons()).isEqualTo(personSet);
        assertThat(projectService.findProject(1).getOrganizations()).isEqualTo(organizationSet);
    }

    @Test
    @Sql(value = {"create-project-before.sql"})
    public void addConnectionToPersonTest(){
        Connect connect = new Connect();
        connect.setOrganization_Id(1);
        connect.setEvent_Id(1);
        connect.setProject_Id(1);
        connectService.addConnections(connect,personService.findPerson(1));
        Event event = eventService.findEvent(1);
        Project project = projectService.findProject(1);
        Organization organization = organizationService.findOrganization(1);
        Set<Event> eventSet = new HashSet<>();
        Set<Project> projectSet = new HashSet<>();
        Set<Organization> organizationSet = new HashSet<>();
        eventSet.add(event);
        projectSet.add(project);
        organizationSet.add(organization);
        assertThat(personService.findPerson(1).getEvents()).isEqualTo(eventSet);
        assertThat(personService.findPerson(1).getProjects()).isEqualTo(projectSet);
        assertThat(personService.findPerson(1).getOrganizations()).isEqualTo(organizationSet);
    }
}
