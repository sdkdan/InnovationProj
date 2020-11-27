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
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.major.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.*;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-project-before.sql"})
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

    @Test
    public void addConnectionToEventTest(){
        Connect connect = new Connect();
        connect.setOrganization_Id(1);
        connect.setEvent_Id(1);
        connect.setPerson_id(1);
        connectService.addConnections(connect,eventService.findEvent(1));
        Project project = projectService.findProject(1);
        Person person = personService.findPerson(1);
        Organization organization = organizationService.findOrganization(1);
        Set<Project> projectSet = new HashSet<>();
        Set<Person> personSet = new HashSet<>();
        Set<Organization> organizationSet = new HashSet<>();
        projectSet.add(project);
        personSet.add(person);
        organizationSet.add(organization);
        assertThat(eventService.findEvent(1).getProjects()).isEqualTo(projectSet);
        assertThat(eventService.findEvent(1).getPersons()).isEqualTo(personSet);
        assertThat(eventService.findEvent(1).getOrganizations()).isEqualTo(organizationSet);
    }

    @Test
    public void addConnectionToOrganizationTest(){
        Connect connect = new Connect();
        connect.setOrganization_Id(1);
        connect.setEvent_Id(1);
        connect.setPerson_id(1);
        connectService.addConnections(connect,organizationService.findOrganization(1));
        Person person = personService.findPerson(1);
        Project project = projectService.findProject(1);
        Event event = eventService.findEvent(1);
        Set<Project> projectSet = new HashSet<>();
        Set<Person> personSet = new HashSet<>();
        Set<Event> eventSet = new HashSet<>();
        projectSet.add(project);
        personSet.add(person);
        eventSet.add(event);
        assertThat(organizationService.findOrganization(1).getEvents()).isEqualTo(eventSet);
        assertThat(organizationService.findOrganization(1).getPersons()).isEqualTo(personSet);
        assertThat(organizationService.findOrganization(1).getProjects()).isEqualTo(projectSet);
    }
    
}
