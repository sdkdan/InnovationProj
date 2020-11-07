package ru.innovat.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.Event;
import ru.innovat.models.utils.TypeEvent;

import java.util.List;

@Repository
public class EventDao {


    private SessionFactory sessionFactory;

    public EventDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Event findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Event.class, id);
    }


    public void add(Event event) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(event);
    }

    public void update(Event event) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(event);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event event = session.load(Event.class, id);

        if (event != null) {
            session.delete(event);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Event> eventList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Event>) session.createQuery("From Event").list();
    }

    public TypeEvent findTypeEventById(int id) {
        return this.sessionFactory.getCurrentSession().get(TypeEvent.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<TypeEvent> findAllTypeEvents() {
        return (List<TypeEvent>) this.sessionFactory.getCurrentSession().createQuery("From TypeEvent").list();
    }

    public Event eventAllConnection(int id) {
        Event event = findById(id);
        Hibernate.initialize(event.getPersons());
        Hibernate.initialize(event.getOrganizations());
        Hibernate.initialize(event.getProjects());
        return event;
    }

}
