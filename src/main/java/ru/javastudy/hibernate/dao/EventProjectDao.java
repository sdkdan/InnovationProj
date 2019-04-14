package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.EventProject;

import java.util.List;


@Repository
public class EventProjectDao {

    @Autowired
    private SessionFactory sessionFactory;



    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(EventProject EventProject) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(EventProject);
    }

    public void delete(int id) { Session session = this.sessionFactory.getCurrentSession();
        EventProject eventProject = (EventProject) session.load(EventProject.class, id);

        if (eventProject != null) {
            session.delete(eventProject);
        }
    }

    @SuppressWarnings("unchecked")
    public List<EventProject> projectList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<EventProject> projectList = (List<EventProject>) session.createQuery("FROM EventProject eventProject WHERE eventProject.id_event = " + id).list();
        return projectList;

    }

    @SuppressWarnings("unchecked")
    public List<EventProject> eventList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        List<EventProject> eventList = (List<EventProject>) session.createQuery("FROM EventProject eventProject WHERE eventProject.id_project = " + id).list();
        return eventList;

    }
}
