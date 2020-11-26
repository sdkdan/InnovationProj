package ru.innovat.dao.authorization;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.authorization.AppUser;

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
        return session.get(AppUser.class, id);
    }

    public AppUser findByUsername(String username) {
        Session session = this.sessionFactory.getCurrentSession();
        return (AppUser) session.createQuery("SELECT U FROM AppUser U WHERE U.username = :username")
                .setParameter("username", username).uniqueResult();
    }

    public void add(AppUser appUser) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(appUser);
    }

    public void update(AppUser appUser) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(appUser);
    }

    public AppUser findByEmail(String email) {
        Session session = this.sessionFactory.getCurrentSession();
        return (AppUser) (session.createQuery("SELECT A FROM AppUser A WHERE eMail =:eMail ")
                .setParameter("eMail", email).uniqueResult());
    }

    @SuppressWarnings("unchecked")
    public List<AppUser> userList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<AppUser>) session.createQuery("From AppUser").list();
    }

    @SuppressWarnings("unchecked")
    public List<AppUser> roleUserList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<AppUser>) session.createQuery("SELECT A From AppUser A WHERE A.role.id_role =:id")
                .setParameter("id", 2).list();
    }
}