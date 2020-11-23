package ru.innovat.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.EventDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.models.utils.TypeEvent;
import ru.innovat.search.EventSearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor(onConstructor=@__({@Lazy}))
public class EventService {
    private final EventDao eventDao;
    private final OrganizationService organizationService;
    private final PersonService personService;
    private final ProjectService projectService;
    private final EventSearch eventSearch;

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

    public Event addOrganization(Organization organization, int id) {
        Event event = eventDao.findById(id);
        event.addOrganization(organization);
        return event;
    }

    public Event addProject(Project project, int id) {
        Event event = eventDao.findById(id);
        event.addProject(project);
        return event;
    }

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

    @Transactional
    public void addConnections(Connect connect, int id){
        if (connect.getProject_Id() >= 1) {
            updateEvent(addProject(projectService.findProject(connect.getProject_Id()), id));
        }
        if (connect.getPerson_id() >= 1) {
            updateEvent(addPerson(personService.findPerson(connect.getPerson_id()), id));
        }
        if (connect.getOrganization_Id() >= 1) {
            updateEvent(addOrganization(organizationService.findOrganization
                    (connect.getOrganization_Id()), id));
        }
    }

    @Transactional
    public List<Event> searchEventList(String search){
        if (search != null) {
            if (search.length() > 0) {
                return eventSearch.fuzzySearch(search);
            } else return eventList();
        } else return eventList();
    }
}
