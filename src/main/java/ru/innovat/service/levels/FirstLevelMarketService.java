package ru.innovat.service.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.levels.FirstLevelMarketDao;
import ru.innovat.models.levels.FirstLevelMarket;

import java.util.List;

@Service
public class FirstLevelMarketService {

    public FirstLevelMarketService(){
    }

    @Autowired
    private FirstLevelMarketDao firstLevelMarketDao = new FirstLevelMarketDao();

    @Transactional
    public FirstLevelMarket findFirstLevelMarket(int id) {
        return firstLevelMarketDao.findById(id);
    }

    @Transactional
    public List<FirstLevelMarket> firstLevelMarketList() {
        return firstLevelMarketDao.firstLevelMarketList();
    }


}







