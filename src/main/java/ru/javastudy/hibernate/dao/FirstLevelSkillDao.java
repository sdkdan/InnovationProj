package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.FirstLevelSkill;

import java.util.List;

@Repository
public class FirstLevelSkillDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public FirstLevelSkill findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        FirstLevelSkill firstLevelSkill  = (FirstLevelSkill) session.load(FirstLevelSkill.class, id);
        return firstLevelSkill;
    }

    @SuppressWarnings("unchecked")
    public List<FirstLevelSkill> firstLevelSkillList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<FirstLevelSkill> firstLevelSkillList = (List<FirstLevelSkill>) session.createQuery("From FirstLevelSkill").list();
        return firstLevelSkillList;
    }

}




