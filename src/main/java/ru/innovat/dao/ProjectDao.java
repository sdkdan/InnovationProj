package ru.innovat.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.Project;

import java.util.List;

@Repository
public class ProjectDao {

    private SessionFactory sessionFactory;

    public ProjectDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Project findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(Project.class, id);
    }


    public void add(Project project) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(project);
    }

    public void update(Project project) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(project);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Project project = session.load(Project.class, id);

        if (project != null) {
            session.delete(project);
        }
    }


    public Project projectAllConnections(int id) {
        Project project = findById(id);
        Hibernate.initialize(project.getPersons());
        Hibernate.initialize(project.getOrganizations());
        Hibernate.initialize(project.getEvents());
        return project;
    }


    @SuppressWarnings("unchecked")
    public List<Project> projectList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Project>) session.createQuery("From Project").list();
    }

}
