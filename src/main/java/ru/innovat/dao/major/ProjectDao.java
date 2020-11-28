package ru.innovat.dao.major;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Project;

import java.util.ArrayList;

@Repository
@AllArgsConstructor
public class ProjectDao {
    private final SessionFactory sessionFactory;

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
        if (project != null) session.delete(project);
    }

    public Project projectAllConnections(int id) {
        Project project = findById(id);
        Hibernate.initialize(project.getPersons());
        Hibernate.initialize(project.getOrganizations());
        Hibernate.initialize(project.getEvents());
        return project;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<Project> projectList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (ArrayList<Project>) session.createQuery("From Project").list();
    }

}
