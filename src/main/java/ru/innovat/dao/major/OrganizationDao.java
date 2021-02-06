package ru.innovat.dao.major;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.innovat.models.major.Organization;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrganizationDao {
    private final SessionFactory sessionFactory;

    @Nullable
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
        if (organization != null) session.delete(organization);
    }

    public void update(Organization organization) {
        Session session = sessionFactory.getCurrentSession();
        session.update(organization);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<Organization> organizationList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Organization>) session.createQuery("From Organization").list();

    }

    @Nullable
    public Organization organizationAllConnection(int id) {
        Organization organization = findById(id);
        Hibernate.initialize(organization.getPersons());
        Hibernate.initialize(organization.getProjects());
        Hibernate.initialize(organization.getEvents());
        return organization;
    }
}
