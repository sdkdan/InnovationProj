package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ThirdLevelTechnologyDao;
import ru.javastudy.hibernate.models.ThirdLevelTechnology;

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













