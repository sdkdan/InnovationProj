package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.SecondLevelTechnologyDao;
import ru.javastudy.hibernate.models.SecondLevelTechnology;

import java.util.List;

@Service
public class SecondLevelTechnologyService {

    public SecondLevelTechnologyService(){
    }

    @Autowired
    private SecondLevelTechnologyDao secondLevelTechnologyDao = new SecondLevelTechnologyDao();

    @Transactional
    public SecondLevelTechnology findSecondLevelTechnology(int id) {
        return secondLevelTechnologyDao.findById(id);
    }

    @Transactional
    public List<SecondLevelTechnology> secondLevelTechnologyList() {
        return secondLevelTechnologyDao.secondLevelTechnologyList();
    }


}









