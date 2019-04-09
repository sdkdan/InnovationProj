package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.dao.PersonDao;
import ru.javastudy.hibernate.models.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {



    public PersonService(){

    }
    @Autowired
    private PersonDao personDao = new PersonDao();

    @Transactional
    public void setPersonDao(PersonDao personDao){
        this.personDao = personDao;
    }

    @Transactional
    public Person findPerson(int id) {
        return this.personDao.findById(id);
    }

    @Transactional
    public void addPerson(Person person) {
        this.personDao.add(person);
    }

    @Transactional
    public void deletePerson(int id) {
        this.personDao.delete(id);
    }

    @Transactional
    public void updatePerson(Person person) {
        this.personDao.update(person);
    }

    @Transactional
    public List<Person> personList() {
        return this.personDao.personList();
    }



}
