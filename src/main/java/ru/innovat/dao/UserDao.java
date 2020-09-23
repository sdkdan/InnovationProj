package ru.innovat.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innovat.models.User;

import java.util.ArrayList;

@Repository
public class UserDao {
    private SessionFactory sessionFactory;

    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User findById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, id);
        return user;
    }


    public User findByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.createQuery("SELECT U FROM User U WHERE U.username = :username").setParameter("username", username).uniqueResult();;
        return user;
    }



    public void add(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(user);
    }

    public void update(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, id);

        if (user != null) {
            session.delete(user);
        }
    }
}