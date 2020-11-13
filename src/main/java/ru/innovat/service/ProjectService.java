package ru.innovat.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.ProjectDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.search.ProjectSearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor(onConstructor=@__({@Lazy}))
public class ProjectService {
    private final ProjectDao projectDao;
    private final OrganizationService organizationService;
    private final EventService eventService;
    private final PersonService personService;
    private final ProjectSearch projectSearch;

    @Transactional
    public Project findProject(int id) {
        return projectDao.findById(id);
    }

    @Transactional
    public void addProject(Project project) {
        this.projectDao.add(project);
    }

    @Transactional
    public void deleteProject(int id) {
        projectDao.delete(id);
    }

    @Transactional
    public void updateProject(Project project) {
        projectDao.update(project);
    }

    @Transactional
    public List<Project> projectList() {
        return projectDao.projectList();
    }

    @Transactional
    public Project projectAllConnections(int id) {
        return projectDao.projectAllConnections(id);
    }

    @Transactional
    public Project saveSets(Project project, int id) {

        Project project1 = new Project();


        project.setOrganizations(project1.getOrganizations());
        project.setPersons(project1.getPersons());
        project.setEvents(project1.getEvents());


        project.setId_project(id);


        return project;
    }


    @Transactional
    public void deleteSets(Project project) {

        Set<Person> persons = new HashSet<>();
        Set<Organization> organizations = new HashSet<>();
        Set<Event> events = new HashSet<>();


        project.setPersons(persons);
        project.setEvents(events);
        project.setOrganizations(organizations);


        projectDao.update(project);
    }

    public Project addEvent(Event event, int id) {
        Project project = projectDao.findById(id);
        project.addEvent(event);
        return project;
    }

    public Project addPerson(Person person, int id) {
        Project project = projectDao.findById(id);
        project.addPerson(person);
        return project;
    }

    public Project addOrganization(Organization organization, int id) {
        Project project = projectDao.findById(id);
        project.addOrganization(organization);
        return project;
    }

    @Transactional
    public void addConnections(Connect connect, int id){
        if (connect.getProject_Id() >= 1) {
            updateProject(addPerson(personService.findPerson(connect.getProject_Id()), id));
        }
        if (connect.getPerson_id() >= 1) {
            updateProject(addEvent(eventService.findEvent(connect.getPerson_id()), id));
        }
        if (connect.getOrganization_Id() >= 1) {
            updateProject(addOrganization(organizationService.findOrganization
                    (connect.getOrganization_Id()), id));
        }
    }

    @Transactional
    public List<Project> searchProjectList(String search){
        if (search != null) {
            if (search.length() > 0) {
                return projectSearch.fuzzySearch(search);
            } else return projectList();
        } else return projectList();
    }
}
