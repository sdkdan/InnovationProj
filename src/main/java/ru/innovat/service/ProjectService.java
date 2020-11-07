package ru.innovat.service;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.ProjectDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class ProjectService {

    public ProjectService(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    private final ProjectDao projectDao;

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

    @Transactional
    public Project addEvent(Event event, int id) {
        Project project = projectDao.findById(id);
        project.addEvent(event);
        return project;
    }

    @Transactional
    public Project addPerson(Person person, int id) {
        Project project = projectDao.findById(id);
        project.addPerson(person);
        return project;
    }

    @Transactional
    public Project addOrganization(Organization organization, int id) {
        Project project = projectDao.findById(id);
        project.addOrganization(organization);
        return project;
    }


}
