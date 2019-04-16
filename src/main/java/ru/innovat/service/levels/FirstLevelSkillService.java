package ru.innovat.service.levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.levels.FirstLevelSkillDao;
import ru.innovat.models.levels.FirstLevelSkill;

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







