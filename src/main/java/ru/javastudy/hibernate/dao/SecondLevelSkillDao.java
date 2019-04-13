package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.SecondLevelSkill;

import java.util.List;

@Repository
public class SecondLevelSkillDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public SecondLevelSkill findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        SecondLevelSkill secondLevelSkill  = (SecondLevelSkill) session.load(SecondLevelSkill.class, id);
        return secondLevelSkill;
    }

    @SuppressWarnings("unchecked")
    public List<SecondLevelSkill> secondLevelSkillList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<SecondLevelSkill> secondLevelSkillList = (List<SecondLevelSkill>) session.createQuery("From SecondLevelSkill").list();
        return secondLevelSkillList;
    }

}

