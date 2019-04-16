package ru.innovat.service.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.levels.SecondLevelSkillDao;
import ru.innovat.models.levels.SecondLevelSkill;

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








