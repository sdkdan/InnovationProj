package ru.innovat.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innovat.models.Event;

import java.util.List;

@Repository
public class EventDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Event findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Event event = (Event) session.get(Event.class, id);
        return event;
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
        Event event = (Event)session.load(Event.class, id);

        if(event != null){
            session.delete(event);
        }
    }
    @SuppressWarnings("unchecked")
    public List<Event> eventList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Event> eventList = (List<Event>) session.createQuery("From Event").list();
        return eventList;

    }

    public Event eventAllConnection(int id){
        Event event = findById(id);
        Hibernate.initialize(event.getPersons());
        Hibernate.initialize(event.getOrganizations());
        Hibernate.initialize(event.getProjects());
        return event;
    }

}
