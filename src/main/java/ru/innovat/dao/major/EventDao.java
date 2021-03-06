package ru.innovat.dao.major;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Event;
import ru.innovat.models.utils.TypeEvent;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventDao {

    private final SessionFactory sessionFactory;

    @Nullable
    public Event findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Event.class, id);
    }

    public void add(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(event);
    }

    public void update(Event event) {
        Session session = sessionFactory.getCurrentSession();
        session.update(event);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Event event = session.load(Event.class, id);
        if (event != null) session.delete(event);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<Event> eventList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Event>) session.createQuery("From Event").list();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<TypeEvent> findAllTypeEvents() {
        return (List<TypeEvent>) sessionFactory.getCurrentSession().createQuery("From TypeEvent").list();
    }

    public Event eventAllConnection(int id) {
        Event event = findById(id);
        if (event == null) {
            return null;
        }
        Hibernate.initialize(event.getPersons());
        Hibernate.initialize(event.getOrganizations());
        Hibernate.initialize(event.getProjects());
        return event;
    }

}
