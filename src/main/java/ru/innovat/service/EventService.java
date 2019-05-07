package ru.innovat.service;

import org.hibernate.hql.internal.ast.ParseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.EventDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.TypeEvent;

import javax.persistence.Table;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EventService {

    public EventService(){

    }

    @Autowired
    private EventDao eventDao = new EventDao();

    @Transactional
    public void setEventDao(EventDao eventDao){
        this.eventDao = eventDao;
    }

    @Transactional
    public Event findEvent(int id) {
        return eventDao.findById(id);
    }

    @Transactional
    public void addEvent(Event event) {
        eventDao.add(event);
    }

    @Transactional
    public void deleteEvent(int id) {
        eventDao.delete(id);
    }

    @Transactional
    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    @Transactional
    public List<Event> eventList() {
        return eventDao.eventList();
    }

    @Transactional
    public Event eventAllConnections(int id){return eventDao.eventAllConnection(id);}

    @Transactional
    public Event saveSets (Event event, int id){
        //Находим исходное мероприятие
        Event event1 = new Event();

        //Перезаписываем связи в измененное мероприятие
        event.setOrganizations(event.getOrganizations());
        event.setProjects(event.getProjects());
        event.setPersons(event.getPersons());

        //Перезаписываем id
        event.setId_event(id);

        //Возврощаем измененное мероприятие с перезаписанными связями
        return event;
    }

    //Удаляет все связи (для того что бцы при удалении объекта не вылетала ошибка)
    @Transactional
    public void deleteSets(Event event){
        //Создаем пустые Set'ы для того что бы удалить все связи у мероприятия
        Set<Project> projects = new HashSet<>();
        Set<Organization> organizations = new HashSet<>();
        Set<Person> persons = new HashSet<>();


        //Записываем эти Set'ы в мероприятие
        event.setProjects(projects);
        event.setPersons(persons);
        event.setOrganizations(organizations);

        //Перезаписываем мероприятие с пустыми Set'ами(Связями)
        eventDao.update(event);
    }

    @Transactional
    public Event addOrganization(Organization organization,int id){
        Event event = eventDao.findById(id);
        event.addOrganization(organization);
        return event;
    }

    @Transactional
    public Event addProject(Project project,int id){
        Event event = eventDao.findById(id);
        event.addProject(project);
        return event;
    }

    @Transactional
    public Event addPerson(Person person, int id){
        Event event = eventDao.findById(id);
        event.addPersons(person);
        return event;
    }

    @Transactional
    public TypeEvent findTypeEventById(int id) {
        return eventDao.findTypeEventById(id);
    }

    @Transactional
    public List<TypeEvent> findAllTypeEvents() {
        return eventDao.findAllTypeEvents();
    }


}
