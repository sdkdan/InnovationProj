package ru.innovat.service.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.levels.FirstLevelTechnology;
import ru.innovat.dao.levels.FirstLevelTechnologyDao;

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
//asdf

}











