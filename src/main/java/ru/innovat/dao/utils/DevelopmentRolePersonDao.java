package ru.innovat.dao.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innovat.models.utils.DevelopmentRolePerson;

import java.util.List;

@Repository
public class DevelopmentRolePersonDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public DevelopmentRolePerson findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        DevelopmentRolePerson developmentRolePerson = (DevelopmentRolePerson) session.load(DevelopmentRolePerson.class, id);
        return developmentRolePerson;
    }


    public void add(DevelopmentRolePerson developmentRolePerson) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(developmentRolePerson);
    }

    public void update(DevelopmentRolePerson developmentRolePerson) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(developmentRolePerson);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        DevelopmentRolePerson developmentRolePerson = (DevelopmentRolePerson)session.load(DevelopmentRolePerson.class, id);

        if(developmentRolePerson != null){
            session.delete(developmentRolePerson);
        }
    }
    @SuppressWarnings("unchecked")
    public List<DevelopmentRolePerson> developmentRolePersonList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<DevelopmentRolePerson> developmentRolePersonList = (List<DevelopmentRolePerson>) session.createQuery("From DevelopmentRolePerson").list();
        return developmentRolePersonList;

    }
}
