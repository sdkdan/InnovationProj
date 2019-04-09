package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.Project;

import java.util.List;

@Repository
public class ProjectDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Project findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Project project = (Project) session.load(Project.class, id);

        return project;
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
        Project project = (Project)session.load(Project.class, id);

        if(project != null){
            session.delete(project);
        }
    }


    @SuppressWarnings("unchecked")
    public List<Project> projectList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Project> projectList = (List<Project>) session.createQuery("From Project").list();
        return projectList;

    }

}
