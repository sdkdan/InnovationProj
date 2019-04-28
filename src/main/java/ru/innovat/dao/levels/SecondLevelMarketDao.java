package ru.innovat.dao.levels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innovat.models.levels.SecondLevelMarket;

import java.util.List;

@Repository
public class SecondLevelMarketDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SecondLevelMarket findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        SecondLevelMarket secondLevelMarket  = (SecondLevelMarket) session.load(SecondLevelMarket.class, id);
        return secondLevelMarket;
    }

    @SuppressWarnings("unchecked")
    public List<SecondLevelMarket> secondLevelMarketList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<SecondLevelMarket> secondLevelMarketList = (List<SecondLevelMarket>) session.createQuery("From SecondLevelMarket").list();
        return secondLevelMarketList;
    }

}
