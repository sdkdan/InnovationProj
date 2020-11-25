package ru.innovat.dao.major;

import org.hibernate.*;

import java.util.List;
import org.springframework.stereotype.Repository;


import ru.innovat.models.major.Person;

@Repository
public class PersonDao {

    private SessionFactory sessionFactory;

    public PersonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Person findById(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }



    public void add(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(person);
    }

    public void update(Person person) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(person);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, id);

        if (person != null) {
            session.delete(person);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Person> personList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Person>) session.createQuery("From Person").list();
    }

    public Person personAllConnections(int id){
        Person person = findById(id);
        Hibernate.initialize(person.getEvents());
        Hibernate.initialize(person.getOrganizations());
        Hibernate.initialize(person.getProjects());
        return person;
    }

}