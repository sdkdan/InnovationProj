package ru.innovat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.PersonDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class PersonService {


    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    private final PersonDao personDao;


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

    @Transactional
    public Person addEvent(Event event, int id) {
        Person person = personDao.findById(id);
        person.addEvent(event);
        return person;
    }

    @Transactional
    public Person addOrganizatin(Organization organization, int id) {
        Person person = personDao.findById(id);
        person.addOrganization(organization);
        return person;
    }

    @Transactional
    public Person addProject(Project project, int id) {
        Person person = personDao.findById(id);
        person.addProject(project);
        return person;
    }
}
