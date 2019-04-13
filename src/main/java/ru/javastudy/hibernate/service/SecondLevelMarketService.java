package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.SecondLevelMarketDao;
import ru.javastudy.hibernate.models.SecondLevelMarket;

import java.util.List;

@Service
public class SecondLevelMarketService {

    public SecondLevelMarketService(){
    }

    @Autowired
    private SecondLevelMarketDao secondLevelMarketDao = new SecondLevelMarketDao();

    @Transactional
    public SecondLevelMarket findSecondLevelMarket(int id) {
        return secondLevelMarketDao.findById(id);
    }

    @Transactional
    public List<SecondLevelMarket> secondLevelSkillList() {
        return secondLevelMarketDao.secondLevelMarketList();
    }


}





