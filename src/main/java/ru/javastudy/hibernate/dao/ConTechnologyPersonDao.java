package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.ConTechnologyPerson;

import java.util.List;

@Repository
public class ConTechnologyPersonDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ConTechnologyPerson findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ConTechnologyPerson conTechnologyPerson  = (ConTechnologyPerson) session.load(ConTechnologyPerson.class, id);
        return conTechnologyPerson;
    }

    @SuppressWarnings("unchecked")
    public List<ConTechnologyPerson> conTechnologyPersonList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ConTechnologyPerson> conTechnologyPersonList = (List<ConTechnologyPerson>) session.createQuery("From ConTechnologyPerson").list();
        return conTechnologyPersonList;

    }

}
