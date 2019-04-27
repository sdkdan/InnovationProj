package ru.innovat.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.innovat.dao.PersonDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.Project;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Сервис отвечающий за таблицу Person попытка 2
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


    //Сохраняем связи при перезаписи
    @Transactional
    public Person saveSets (Person person , int id){
        //Находим иcходную персону
        Person person1 = new Person();

        //Перезаписываем связи в измененную персону
        person.setOrganizations(person1.getOrganizations());
        person.setProjects(person1.getProjects());
        person.setEvents(person1.getEvents());

        //Перезаписываем id
        person.setId_person(id);

        //Возврощаем измененную персону с перезаписанными связями
        return person;
    }

    //Удаляет все связи (для того что бцы при удалении объекта не вылетала ошибка)
    @Transactional
    public void deleteSets(Person person){
        //Создаем пустые Set'ы для того что бы удалить все связи у персоны
        Set<Project> projects = new HashSet<>();
        Set<Organization> organizations = new HashSet<>();
        Set<Event> events = new HashSet<>();


        //Записываем эти Set'ы в персону
        person.setProjects(projects);
        person.setEvents(events);
        person.setOrganizations(organizations);

        //Перезаписываем персону с пустыми Set'ами(Связями)
        personDao.update(person);
    }
}
