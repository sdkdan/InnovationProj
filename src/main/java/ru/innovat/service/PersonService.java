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

}
