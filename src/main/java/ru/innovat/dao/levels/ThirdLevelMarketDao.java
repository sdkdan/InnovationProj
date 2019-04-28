package ru.innovat.dao.levels;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innovat.models.levels.ThirdLevelMarket;

import java.util.List;

@Repository
public class ThirdLevelMarketDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ThirdLevelMarket findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ThirdLevelMarket thirdLevelMarket  = (ThirdLevelMarket) session.load(ThirdLevelMarket.class, id);
        return thirdLevelMarket;
    }

    @SuppressWarnings("unchecked")
    public List<ThirdLevelMarket> thirdLevelMarketList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ThirdLevelMarket> thirdLevelMarketList = (List<ThirdLevelMarket>) session.createQuery("From ThirdLevelMarket").list();
        return thirdLevelMarketList;
    }

}





