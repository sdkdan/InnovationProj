package ru.innovat.service.major;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Organization;
import ru.innovat.models.major.Person;
import ru.innovat.models.major.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

@Service
@RequiredArgsConstructor
public class ConnectionService {
    private final OrganizationService organizationService;
    private final EventService eventService;
    private final PersonService personService;
    private final ProjectService projectService;
    private static final int oneConnect = 1;

    @Transactional
    public void addConnections(Connect connect, Event event){
        if (connect.getProject_Id() >= oneConnect) {
            event.addProject(projectService.findProject(connect.getProject_Id()));
        }
        if (connect.getPerson_id() >= oneConnect) {
            event.addPersons(personService.findPerson(connect.getPerson_id()));
        }
        if (connect.getOrganization_Id() >= oneConnect) {
            event.addOrganization(organizationService.findOrganization(connect.getOrganization_Id()));
        }
        eventService.updateEvent(event);
    }

    @Transactional
    public void addConnections(Connect connect, Person person){
        if (connect.getProject_Id() >= oneConnect) {
            person.addProject(projectService.findProject(connect.getProject_Id()));
        }
        if (connect.getEvent_Id() >= oneConnect) {
            person.addEvent(eventService.findEvent(connect.getEvent_Id()));
        }
        if (connect.getOrganization_Id() >= oneConnect) {
            person.addOrganization(organizationService.findOrganization(connect.getOrganization_Id()));
        }
        personService.updatePerson(person);
    }

    @Transactional
    public void addConnections(Connect connect, Project project){
        if (connect.getPerson_id() >= oneConnect) {
            project.addPerson(personService.findPerson(connect.getPerson_id()));
        }
        if (connect.getEvent_Id() >= oneConnect) {
            project.addEvent(eventService.findEvent(connect.getEvent_Id()));
        }
        if (connect.getOrganization_Id() >= oneConnect) {
            project.addOrganization(organizationService.findOrganization(connect.getOrganization_Id()));
        }
        projectService.updateProject(project);
    }

    @Transactional
    public void addConnections(Connect connect, Organization organization){
        if (connect.getPerson_id() >= oneConnect) {
            organization.addPerson(personService.findPerson(connect.getPerson_id()));
        }
        if (connect.getProject_Id() >= oneConnect) {
            organization.addProject(projectService.findProject(connect.getProject_Id()));
        }
        if (connect.getEvent_Id() >= oneConnect) {
            organization.addEvent(eventService.findEvent(connect.getEvent_Id()));
        }
        organizationService.updateOrganization(organization);
    }
}
