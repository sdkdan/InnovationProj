package ru.innovat.dao;


import org.hibernate.Session;
import ru.innovat.models.Organization;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class OrganizationDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Organization findById(int id){
       return sessionFactory.getCurrentSession().get(Organization.class , id);
    }

    public void add(Organization organization){
        Session session = sessionFactory.getCurrentSession();
        session.persist(organization);
    }


    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        Organization organization = (Organization)session.load(Organization.class, id);

        if(organization != null){
            session.delete(organization);
        }
    }

    public void update(Organization organization){
        Session session = sessionFactory.getCurrentSession();
        session.update(organization);
    }

    @SuppressWarnings("unchecked")
    public List<Organization> organizationList() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Organization> organizationList = (List<Organization>) session.createQuery("From Organization").list();
        return organizationList;

    }
}
