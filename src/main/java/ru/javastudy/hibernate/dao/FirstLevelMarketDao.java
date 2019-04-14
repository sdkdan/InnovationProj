package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.FirstLevelMarket;

import java.util.List;

@Repository
public class FirstLevelMarketDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public FirstLevelMarket findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        FirstLevelMarket firstLevelMarket  = (FirstLevelMarket) session.load(FirstLevelMarket.class, id);
        return firstLevelMarket;
    }

    @SuppressWarnings("unchecked")
    public List<FirstLevelMarket> firstLevelMarketList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<FirstLevelMarket> firstLevelMarketList = (List<FirstLevelMarket>) session.createQuery("From FirstLevelMarket").list();
        return firstLevelMarketList;
    }

}


