package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.ConTechnologyProject;

import java.util.List;

@Repository
public class ConTechnologyProjectDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ConTechnologyProject findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ConTechnologyProject conTechnologyProject  = (ConTechnologyProject) session.load(ConTechnologyProject.class, id);
        return conTechnologyProject;
    }

    @SuppressWarnings("unchecked")
    public List<ConTechnologyProject> conTechnologyProjectList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ConTechnologyProject> conTechnologyProjectList = (List<ConTechnologyProject>) session.createQuery("From ConTechnologyProject").list();
        return conTechnologyProjectList;

    }

}
