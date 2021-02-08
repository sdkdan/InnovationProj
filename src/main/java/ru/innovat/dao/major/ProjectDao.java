package ru.innovat.dao.major;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Project;

import java.util.ArrayList;

@Repository
@RequiredArgsConstructor
public class ProjectDao {

    private final SessionFactory sessionFactory;

    @Nullable
    public Project findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Project.class, id);
    }

    public void add(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(project);
    }

    public void update(Project project) {
        Session session = sessionFactory.getCurrentSession();
        session.update(project);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Project project = session.load(Project.class, id);
        if (project != null) session.delete(project);
    }

    public Project projectAllConnections(int id) {
        Project project = findById(id);
        if (project == null) {
            return null;
        }
        Hibernate.initialize(project.getPersons());
        Hibernate.initialize(project.getOrganizations());
        Hibernate.initialize(project.getEvents());
        return project;
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public ArrayList<Project> projectList() {
        Session session = sessionFactory.getCurrentSession();
        return (ArrayList<Project>) session.createQuery("From Project").list();
    }

}
