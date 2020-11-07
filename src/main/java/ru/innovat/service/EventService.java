package ru.innovat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.EventDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.TypeEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class EventService {

    public EventService(EventDao eventDao) {

        this.eventDao = eventDao;
    }

    private final EventDao eventDao;



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
    public Event eventAllConnections(int id) {
        return eventDao.eventAllConnection(id);
    }

    @Transactional
    public Event saveSets(Event event, int id) {
        Event event1 = new Event();

        event.setOrganizations(event1.getOrganizations());
        event.setProjects(event1.getProjects());
        event.setPersons(event1.getPersons());


        event.setId_event(id);


        return event;
    }


    @Transactional
    public void deleteSets(Event event) {

        Set<Project> projects = new HashSet<>();
        Set<Organization> organizations = new HashSet<>();
        Set<Person> persons = new HashSet<>();


        event.setProjects(projects);
        event.setPersons(persons);
        event.setOrganizations(organizations);


        eventDao.update(event);
    }

    @Transactional
    public Event addOrganization(Organization organization, int id) {
        Event event = eventDao.findById(id);
        event.addOrganization(organization);
        return event;
    }

    @Transactional
    public Event addProject(Project project, int id) {
        Event event = eventDao.findById(id);
        event.addProject(project);
        return event;
    }

    @Transactional
    public Event addPerson(Person person, int id) {
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
