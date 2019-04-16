package ru.innovat.service.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.levels.ThirdLevelTechnologyDao;
import ru.innovat.models.levels.ThirdLevelTechnology;

import java.util.List;

@Service
public class ThirdLevelTechnologyService {

    public ThirdLevelTechnologyService(){
    }

    @Autowired
    private ThirdLevelTechnologyDao thirdLevelTechnologyDao = new ThirdLevelTechnologyDao();

    @Transactional
    public ThirdLevelTechnology findThirdLevelTechnology(int id) {
        return thirdLevelTechnologyDao.findById(id);
    }

    @Transactional
    public List<ThirdLevelTechnology> thirdLevelTechnologyList() {
        return thirdLevelTechnologyDao.thirdLevelTechnologyList();
    }


}













