package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.ConTechnologyOrganization;

import java.util.List;

@Repository
public class ConTechnologyOrganizationDao {


    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public ConTechnologyOrganization findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ConTechnologyOrganization conTechnologyOrganization  = (ConTechnologyOrganization) session.load(ConTechnologyOrganization.class, id);
        return conTechnologyOrganization;
    }

    @SuppressWarnings("unchecked")
    public List<ConTechnologyOrganization> conTechnologyOrganizationList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<ConTechnologyOrganization> conTechnologyOrganizationList = (List<ConTechnologyOrganization>) session.createQuery("From ConTechnologyOrganization").list();
        return conTechnologyOrganizationList;

    }

}
