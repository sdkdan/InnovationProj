package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.SecondLevelTechnology;

import java.util.List;

@Repository
public class SecondLevelTechnologyDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SecondLevelTechnology findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        SecondLevelTechnology secondLevelTechnology  = (SecondLevelTechnology) session.load(SecondLevelTechnology.class, id);
        return secondLevelTechnology;
    }

    @SuppressWarnings("unchecked")
    public List<SecondLevelTechnology> secondLevelTechnologyList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<SecondLevelTechnology> secondLevelTechnologyList = (List<SecondLevelTechnology>) session.createQuery("From SecondLevelTechnology").list();
        return secondLevelTechnologyList;
    }

}



