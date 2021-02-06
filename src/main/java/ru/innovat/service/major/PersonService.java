package ru.innovat.service.major;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.major.PersonDao;
import ru.innovat.models.major.Person;

import java.util.List;


@Service
@RequiredArgsConstructor
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
