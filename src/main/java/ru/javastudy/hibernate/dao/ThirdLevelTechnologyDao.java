package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.ThirdLevelTechnology;

import java.util.List;

@Repository
public class ThirdLevelTechnologyDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ThirdLevelTechnology findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ThirdLevelTechnology thirdLevelTechnology  = (ThirdLevelTechnology) session.load(ThirdLevelTechnology.class, id);
        return thirdLevelTechnology;
    }

    @SuppressWarnings("unchecked")
    public List<ThirdLevelTechnology> thirdLevelTechnologyList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ThirdLevelTechnology> thirdLevelTechnologyList = (List<ThirdLevelTechnology>) session.createQuery("From ThirdLevelTechnology").list();
        return thirdLevelTechnologyList;
    }

}







