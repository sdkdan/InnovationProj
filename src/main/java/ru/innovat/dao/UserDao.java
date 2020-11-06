package ru.innovat.dao;



import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.AppUser;

import java.util.List;


@Repository
@Transactional
public class UserDao {
    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AppUser findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        return (AppUser) session.get(AppUser.class, id);
    }


    public AppUser findByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        AppUser appUser = (AppUser) session.createQuery("SELECT U FROM AppUser U WHERE U.username = :username").setParameter("username", username).uniqueResult();;
        return appUser;
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

    @SuppressWarnings("unchecked")
    public List<AppUser> userList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<AppUser>) session.createQuery("From AppUser").list();
    }

    @SuppressWarnings("unchecked")
    public List<AppUser> roleUserList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<AppUser>) session.createQuery("SELECT A From AppUser A WHERE A.id_role.id_role =:id").setParameter("id",2).list();
    }
}