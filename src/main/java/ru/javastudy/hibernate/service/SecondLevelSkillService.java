package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.SecondLevelSkillDao;
import ru.javastudy.hibernate.models.SecondLevelSkill;

import java.util.List;

@Service
public class SecondLevelSkillService {

    public SecondLevelSkillService(){
    }

    @Autowired
    private SecondLevelSkillDao secondLevelSkillDao = new SecondLevelSkillDao();

    @Transactional
    public SecondLevelSkill findSecondLevelSkill(int id) {
        return secondLevelSkillDao.findById(id);
    }

    @Transactional
    public List<SecondLevelSkill> secondLevelSkillList() {
        return secondLevelSkillDao.secondLevelSkillList();
    }


}








