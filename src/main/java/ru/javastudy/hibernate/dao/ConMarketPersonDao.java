package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.ConMarketPerson;

import java.util.List;

@Repository
public class ConMarketPersonDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ConMarketPerson findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ConMarketPerson conMarketPerson = (ConMarketPerson) session.load(ConMarketPerson.class, id);
        return conMarketPerson;
    }

    @SuppressWarnings("unchecked")
    public List<ConMarketPerson> conMarketPersonList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ConMarketPerson> conMarketPersonList = (List<ConMarketPerson>) session.createQuery("From ConMarketPerson").list();
        return conMarketPersonList;

    }

}
