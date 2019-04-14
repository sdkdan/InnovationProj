package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.FirstLevelSkillDao;
import ru.javastudy.hibernate.models.FirstLevelSkill;

import java.util.List;

@Service
public class FirstLevelSkillService {

    public FirstLevelSkillService(){
    }

    @Autowired
    private FirstLevelSkillDao firstLevelSkillDao = new FirstLevelSkillDao();

    @Transactional
    public FirstLevelSkill findFirstLevelSkill(int id) {
        return firstLevelSkillDao.findById(id);
    }

    @Transactional
    public List<FirstLevelSkill> firstLevelSkillList() {
        return firstLevelSkillDao.firstLevelSkillList();
    }


}







