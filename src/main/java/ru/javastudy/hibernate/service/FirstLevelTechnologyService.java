package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.FirstLevelTechnologyDao;
import ru.javastudy.hibernate.models.FirstLevelTechnology;

import java.util.List;

@Service
public class FirstLevelTechnologyService {

    public FirstLevelTechnologyService(){
    }

    @Autowired
    private FirstLevelTechnologyDao firstLevelTechnologyDao = new FirstLevelTechnologyDao();

    @Transactional
    public FirstLevelTechnology findFirstLevelTechnology(int id) {
        return firstLevelTechnologyDao.findById(id);
    }

    @Transactional
    public List<FirstLevelTechnology> firstLevelTechnologyList() {
        return firstLevelTechnologyDao.firstLevelTechnologyList();
    }


}











