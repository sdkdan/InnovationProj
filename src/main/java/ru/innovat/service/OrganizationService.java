package ru.innovat.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.OrganizationDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationDao organizationDao;
    private final PersonService personService;
    private final EventService eventService;
    private final ProjectService projectService;


    @Transactional
    public Organization findOrganization(int id) {
        return this.organizationDao.findById(id);
    }

    @Transactional
    public void addOrganization(Organization organization) {
        this.organizationDao.add(organization);
    }

    @Transactional
    public void deleteOrganiztion(int id) {
        this.organizationDao.delete(id);
    }

    @Transactional
    public void updateOrganization(Organization organizaion) {
        this.organizationDao.update(organizaion);
    }

    @Transactional
    public List<Organization> organizationList() {
        return this.organizationDao.organizationList();
    }

    @Transactional
    public Organization organizationAllConnection(int id){return organizationDao.organizationAllConnection(id);}


    @Transactional
    public Organization saveSets (Organization organization , int id){

        Organization organization1 = new Organization();


        organization.setEvents(organization1.getEvents());
        organization.setProjects(organization1.getProjects());
        organization.setPersons(organization1.getPersons());


        organization.setId_organization(id);


        return organization;
    }


    @Transactional
    public void deleteSets(Organization organization){
        Set<Project> projects = new HashSet<>();
        Set<Person> persons = new HashSet<>();
        Set<Event> events = new HashSet<>();



        organization.setProjects(projects);
        organization.setEvents(events);
        organization.setPersons(persons);


        organizationDao.update(organization);
    }

    @Transactional
    public Organization addEvent(Event event,int id){
        Organization organization = organizationDao.findById(id);
        organization.addEvent(event);
        return organization;
    }

    @Transactional
    public Organization addPerson(Person person,int id){
        Organization organization = organizationDao.findById(id);
        organization.addPerson(person);
        return organization;
    }

    @Transactional
    public Organization addPoject(Project project, int id){
        Organization organization = organizationDao.findById(id);
        organization.addProject(project);
        return organization;
    }


    public void addConnections(Connect connect,int id){
        if (connect.getPerson_id() >= 1) {
            updateOrganization(addPerson(personService.findPerson(connect.getPerson_id()), id));
        }
        if (connect.getProject_Id() >= 1) {
            updateOrganization(addPoject(projectService.findProject(connect.getProject_Id()), id));
        }
        if (connect.getEvent_Id() >= 1) {
            updateOrganization(addEvent(eventService.findEvent(connect.getEvent_Id()), id));
        }
    }

}
