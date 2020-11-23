package ru.innovat.dao.major;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Organization;

import java.util.List;

@Repository
public class OrganizationDao {

    private SessionFactory sessionFactory;

    public OrganizationDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Organization findById(int id) {
        return sessionFactory.getCurrentSession().get(Organization.class, id);
    }

    public void add(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(organization);
    }


    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        Organization organization = session.load(Organization.class, id);

        if (organization != null) {
            session.delete(organization);
        }
    }

    public void update(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.update(organization);
    }

    @SuppressWarnings("unchecked")
    public List<Organization> organizationList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<Organization>) session.createQuery("From Organization").list();

    }

    public Organization organizationAllConnection(int id) {
        Organization organization = findById(id);
        Hibernate.initialize(organization.getPersons());
        Hibernate.initialize(organization.getProjects());
        Hibernate.initialize(organization.getEvents());
        return organization;
    }
}
