package ru.innovat.service.major;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class ConnectionService {
    private final OrganizationService organizationService;
    private final EventService eventService;
    private final PersonService personService;
    private final ProjectService projectService;

    @Transactional
    public void addConnections(Connect connect, Event event){
        if (connect.getProject_Id() >= 1) {
            event.addProject(projectService.findProject(connect.getProject_Id()));
        }
        if (connect.getPerson_id() >= 1) {
            event.addPersons(personService.findPerson(connect.getPerson_id()));
        }
        if (connect.getOrganization_Id() >= 1) {
            event.addOrganization(organizationService.findOrganization(connect.getOrganization_Id()));
        }
        eventService.updateEvent(event);
    }

    @Transactional
    public void addConnections(Connect connect, Person person){
        if (connect.getProject_Id() >= 1) {
            System.out.println(person.getComment());
            person.addProject(projectService.findProject(connect.getProject_Id()));
        }
        if (connect.getEvent_Id() >= 1) {
            person.addEvent(eventService.findEvent(connect.getEvent_Id()));
        }
        if (connect.getOrganization_Id() >= 1) {
            person.addOrganization(organizationService.findOrganization(connect.getOrganization_Id()));
        }
        personService.updatePerson(person);
    }

    @Transactional
    public void addConnections(Connect connect, Project project){
        if (connect.getPerson_id() >= 1) {
            project.addPerson(personService.findPerson(connect.getPerson_id()));
        }
        if (connect.getEvent_Id() >= 1) {
            project.addEvent(eventService.findEvent(connect.getEvent_Id()));
        }
        if (connect.getOrganization_Id() >= 1) {
            project.addOrganization(organizationService.findOrganization(connect.getOrganization_Id()));
        }
        projectService.updateProject(project);
    }

    @Transactional
    public void addConnections(Connect connect, Organization organization){
        if (connect.getPerson_id() >= 1) {
            organization.addPerson(personService.findPerson(connect.getPerson_id()));
        }
        if (connect.getProject_Id() >= 1) {
            organization.addProject(projectService.findProject(connect.getProject_Id()));
        }
        if (connect.getEvent_Id() >= 1) {
            organization.addEvent(eventService.findEvent(connect.getEvent_Id()));
        }
        organizationService.updateOrganization(organization);
    }
}
