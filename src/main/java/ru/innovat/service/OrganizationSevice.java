package ru.innovat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.OrganizationDao;
import ru.innovat.dao.PersonDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrganizationSevice {



    public OrganizationSevice(OrganizationDao organizationDao){
        this.organizationDao = organizationDao;
    }

    private OrganizationDao organizationDao;

    @Transactional
    public void setOrganizationDao(OrganizationDao organizationDao){
        this.organizationDao = organizationDao;
    }

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

    //Сохраняем связи при перезаписи
    @Transactional
    public Organization saveSets (Organization organization , int id){
        //Находим иcходную организацию
        Organization organization1 = new Organization();

        //Перезаписываем связи в измененную организацию
        organization.setEvents(organization1.getEvents());
        organization.setProjects(organization1.getProjects());
        organization.setPersons(organization1.getPersons());

        //Перезаписываем id
        organization.setId_organization(id);

        //Возврощаем измененную организацию с перезаписанными связями
        return organization;
    }
    //
    //Удаляет все связи (для того что бцы при удалении объекта не вылетала ошибка)
    @Transactional
    public void deleteSets(Organization organization){
        //Создаем пустые Set'ы для того что бы удалить все связи у организации
        Set<Project> projects = new HashSet<>();
        Set<Person> persons = new HashSet<>();
        Set<Event> events = new HashSet<>();


        //Записываем эти Set'ы в организацию
        organization.setProjects(projects);
        organization.setEvents(events);
        organization.setPersons(persons);

        //Перезаписываем персону с пустыми Set'ами(Связями)
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



}
