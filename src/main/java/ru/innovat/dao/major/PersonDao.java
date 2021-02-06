package ru.innovat.dao.major;

import lombok.RequiredArgsConstructor;
import org.hibernate.*;

import java.util.List;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;


import ru.innovat.models.major.Person;

@Repository
@RequiredArgsConstructor
public class PersonDao {
    private final SessionFactory sessionFactory;

    @Nullable
    public Person findById(int id) {
        return sessionFactory.getCurrentSession().get(Person.class, id);
    }

    public void add(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(person);
    }

    public void update(Person person) {
        Session session = sessionFactory.getCurrentSession();
        session.update(person);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Person person = session.load(Person.class, id);
        if (person != null) session.delete(person);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<Person> personList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Person>) session.createQuery("From Person").list();
    }

    @Nullable
    public Person personAllConnections(int id){
        Person person = findById(id);
        Hibernate.initialize(person.getEvents());
        Hibernate.initialize(person.getOrganizations());
        Hibernate.initialize(person.getProjects());
        return person;
    }

}
