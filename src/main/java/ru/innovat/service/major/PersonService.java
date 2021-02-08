package ru.innovat.service.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innovat.dao.major.PersonDao;
import ru.innovat.models.major.Person;

import java.util.List;


@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonDao personDao;

    public Person findPerson(int id) {
        return personDao.findById(id);
    }

    public void addPerson(Person person) {
        personDao.add(person);
    }

    public void deletePerson(int id) {
        personDao.delete(id);
    }

    public void updatePerson(Person person) {
        personDao.update(person);
    }

    public List<Person> personList() {
        return personDao.personList();
    }

    public Person personAllConnections(int id) {
        return personDao.personAllConnections(id);
    }
}
