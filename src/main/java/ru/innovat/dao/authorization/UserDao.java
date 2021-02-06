package ru.innovat.dao.authorization;


import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.authorization.AppUser;

import java.util.List;


@Repository
@RequiredArgsConstructor
public class UserDao {
    private final SessionFactory sessionFactory;

    @Nullable
    public AppUser findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AppUser.class, id);
    }

    @Nullable
    public AppUser findByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        return (AppUser) session.createQuery("SELECT U FROM AppUser U WHERE U.username = :username")
                .setParameter("username", username).uniqueResult();
    }

    public void add(AppUser appUser) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(appUser);
    }

    public void update(AppUser appUser) {
        Session session = sessionFactory.getCurrentSession();
        session.update(appUser);
    }

    @Nullable
    public AppUser findByEmail(String email) {
        Session session = sessionFactory.getCurrentSession();
        return (AppUser) (session.createQuery("SELECT A FROM AppUser A WHERE eMail =:eMail ")
                .setParameter("eMail", email).uniqueResult());
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<AppUser> userList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<AppUser>) session.createQuery("From AppUser").list();
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<AppUser> roleUserList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<AppUser>) session.createQuery("SELECT A From AppUser A WHERE A.role.id_role =:id")
                .setParameter("id", 2).list();
    }
}