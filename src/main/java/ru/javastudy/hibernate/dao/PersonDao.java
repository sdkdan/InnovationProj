package ru.javastudy.hibernate.dao;

import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import ru.javastudy.hibernate.models.Person;

@Repository
public class PersonDao {

//    private static final Logger logger = LoggerFactory.getLogger(PersonDao.class);

    @Autowired
    private SessionFactory sessionFactory;



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
        Person person = (Person) session.load(Person.class, id);

        if (person != null) {
            session.delete(person);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Person> personList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Person> personList = (List<Person>) session.createQuery("From Person").list();
        return personList;

    }

}
