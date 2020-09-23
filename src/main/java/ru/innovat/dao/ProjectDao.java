package ru.innovat.dao;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;

import java.util.List;
import java.util.Set;

@Repository
public class ProjectDao {


    private SessionFactory sessionFactory;

    public ProjectDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void  setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Project findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (Project) session.get(Project.class, id);
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




    public Project projectAllConnections(int id){
        Project project = findById(id);
        Hibernate.initialize(project.getPersons());
        Hibernate.initialize(project.getOrganizations());
        Hibernate.initialize(project.getEvents());
        return project;
    }



    @SuppressWarnings("unchecked")
    public List<Project> projectList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Project> projectList = (List<Project>) session.createQuery("From Project").list();
        return projectList;

    }

}
