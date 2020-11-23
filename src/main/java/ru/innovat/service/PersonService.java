package ru.innovat.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.PersonDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.search.PersonSearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor(onConstructor=@__({@Lazy}))
public class PersonService {
    private final PersonDao personDao;
    private final OrganizationService organizationService;
    private final EventService eventService;
    private final ProjectService projectService;
    private final PersonSearch personSearch;

    @Transactional
    public Person findPerson(int id) {
        return personDao.findById(id);
    }

    @Transactional
    public void addPerson(Person person) {
        this.personDao.add(person);
    }

    @Transactional
    public void deletePerson(int id) {
        personDao.delete(id);
    }

    @Transactional
    public void updatePerson(Person person) {
        personDao.update(person);
    }

    @Transactional
    public List<Person> personList() {
        return personDao.personList();
    }

    @Transactional
    public Person personAllConnections(int id) {
        return personDao.personAllConnections(id);
    }


    @Transactional
    public Person saveSets(Person person, int id) {
        Person person1 = new Person();
        person.setOrganizations(person1.getOrganizations());
        person.setProjects(person1.getProjects());
        person.setEvents(person1.getEvents());
        person.setId_person(id);
        return person;
    }

    @Transactional
    public void deleteSets(Person person) {
        Set<Project> projects = new HashSet<>();
        Set<Organization> organizations = new HashSet<>();
        Set<Event> events = new HashSet<>();
        person.setProjects(projects);
        person.setEvents(events);
        person.setOrganizations(organizations);
        personDao.update(person);
    }

    public Person addEvent(Event event, int id) {
        Person person = personDao.findById(id);
        person.addEvent(event);
        return person;
    }

    public Person addOrganization(Organization organization, int id) {
        Person person = personDao.findById(id);
        person.addOrganization(organization);
        return person;
    }


    public Person addProject(Project project, int id) {
        Person person = personDao.findById(id);
        person.addProject(project);
        return person;
    }

    @Transactional
    public void addConnections(Connect connect, int id){
        if (connect.getProject_Id() >= 1) {
            updatePerson(addProject(projectService.findProject(connect.getProject_Id()), id));
        }
        if (connect.getPerson_id() >= 1) {
            updatePerson(addEvent(eventService.findEvent(connect.getPerson_id()), id));
        }
        if (connect.getOrganization_Id() >= 1) {
            updatePerson(addOrganization(organizationService.findOrganization
                    (connect.getOrganization_Id()), id));
        }
    }

    @Transactional
    public List<Person> searchPersonList(String search){
        if (search != null) {
            if (search.length() > 0) {
                return personSearch.fuzzySearch(search);
            } else return personList();
        } else return personList();
    }
}
