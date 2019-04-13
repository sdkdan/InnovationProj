package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ThirdLevelMarketDao;
import ru.javastudy.hibernate.models.ThirdLevelMarket;

import java.util.List;

@Service
public class ThirdLevelMarketService {

    public ThirdLevelMarketService(){
    }

    @Autowired
    private ThirdLevelMarketDao thirdLevelMarketDao = new ThirdLevelMarketDao();

    @Transactional
    public ThirdLevelMarket findThirdLevelMarkety(int id) {
        return thirdLevelMarketDao.findById(id);
    }

    @Transactional
    public List<ThirdLevelMarket> thirdLevelMarketList() {
        return thirdLevelMarketDao.thirdLevelMarketList();
    }


}











