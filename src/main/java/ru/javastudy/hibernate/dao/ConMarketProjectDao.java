package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.ConMarketProject;

import java.util.List;

@Repository
public class ConMarketProjectDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ConMarketProject findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ConMarketProject conMarketProject  = (ConMarketProject) session.load(ConMarketProject.class, id);
        return conMarketProject;
    }

    @SuppressWarnings("unchecked")
    public List<ConMarketProject> conMarketProjectList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ConMarketProject> conMarketProjectList = (List<ConMarketProject>) session.createQuery("From ConMarketProject").list();
        return conMarketProjectList;

    }

}
