package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.FirstLevelTechnology;

import java.util.List;

@Repository
public class FirstLevelTechnologyDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public FirstLevelTechnology findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        FirstLevelTechnology firstLevelTechnology  = (FirstLevelTechnology) session.load(FirstLevelTechnology.class, id);
        return firstLevelTechnology;
    }

    @SuppressWarnings("unchecked")
    public List<FirstLevelTechnology> firstLevelTechnologyList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<FirstLevelTechnology> firstLevelTechnologyList = (List<FirstLevelTechnology>) session.createQuery("From FirstLevelTechnology").list();
        return firstLevelTechnologyList;
    }

}





