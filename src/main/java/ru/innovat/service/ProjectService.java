package ru.innovat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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

    public ProjectService(){

    }

    @Autowired
    private ProjectDao projectDao = new ProjectDao();

    @Transactional
    public void setProjectDao(ProjectDao projectDao){
        this.projectDao = projectDao;
    }

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
    public Project projectAllConnections(int id){return projectDao.projectAllConnections(id);}

    @Transactional
    public Project saveSets (Project project , int id){
        //Находим исходный проект
        Project project1 = new Project();

        //Перезаписываем связи в измененный проект
        project.setOrganizations(project1.getOrganizations());
        project.setPersons(project1.getPersons());
        project.setEvents(project1.getEvents());

        //Перезаписываем id
        project.setId_project(id);

        //Возврощаем измененный проект с перезаписанными связями
        return project;
    }

    //Удаляет все связи (для того что бцы при удалении объекта не вылетала ошибка)
    @Transactional
    public void deleteSets(Project project){
        //Создаем пустые Set'ы для того что бы удалить все связи у проекта
        Set<Person> persons = new HashSet<>();
        Set<Organization> organizations = new HashSet<>();
        Set<Event> events = new HashSet<>();


        //Записываем эти Set'ы в персону
        project.setPersons(persons);
        project.setEvents(events);
        project.setOrganizations(organizations);

        //Перезаписываем проект с пустыми Set'ами(Связями)
        projectDao.update(project);
    }

    @Transactional
    public Project addEvent(Event event , int id){
        Project project = projectDao.findById(id);
        project.addEvent(event);
        return  project;
    }

    @Transactional
    public Project addPerson(Person person,int id){
        Project project = projectDao.findById(id);
        project.addPerson(person);
        return project;
    }

    @Transactional
    public Project addOrganization(Organization organization,int id){
        Project project = projectDao.findById(id);
        project.addOrganization(organization);
        return project;
    }
}
