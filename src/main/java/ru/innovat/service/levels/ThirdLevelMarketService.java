package ru.innovat.service.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.levels.ThirdLevelMarketDao;
import ru.innovat.models.levels.ThirdLevelMarket;

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











