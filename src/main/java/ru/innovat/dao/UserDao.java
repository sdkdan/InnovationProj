package ru.innovat.dao;


import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


@Repository
@Transactional
public class UserDao {
    private SessionFactory sessionFactory;
    private EntityManager entityManager;

    public UserDao(SessionFactory sessionFactory, EntityManager entityManager) {
        this.sessionFactory = sessionFactory;
        this.entityManager = entityManager;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AppUser findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        AppUser appUser = (AppUser) session.get(AppUser.class, id);
        return appUser;
    }


    public AppUser findByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        AppUser appUser = (AppUser) session.createQuery("SELECT U FROM AppUser U WHERE U.username = :username").setParameter("username", username).uniqueResult();;
        return appUser;
    }

    public AppUser findUserAccount(String username) {
        try {
            String sql = "Select e from " + AppUser.class.getName() + " e " //
                    + " Where e.username = :username ";

            Query query = (Query) entityManager.createQuery(sql, AppUser.class);
            query.setParameter("username", username);

            return (AppUser) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void add(AppUser appUser) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(appUser);
    }

    public void update(AppUser appUser) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(appUser);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        AppUser appUser = (AppUser) session.load(AppUser.class, id);

        if (appUser != null) {
            session.delete(appUser);
        }
    }
}