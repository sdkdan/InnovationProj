package ru.innovat.dao.authorization;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.authorization.Blocked;

@Repository
public class BlockedDao {
    private SessionFactory sessionFactory;

    public BlockedDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Blocked findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Blocked.class, id);
    }

    public void add(Blocked blocked) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(blocked);
    }

//    @SuppressWarnings("unchecked")
//    public List<Blocked> blockedList() {
//        Session session = sessionFactory.getCurrentSession();
//        return (List<Blocked>) session.createQuery("SELECT * FROM Blocked");
//    }
//
//    public Blocked findByUserId(int id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        return (Blocked) session.createQuery("SELECT B FROM Blocked B WHERE B.appUser.id_user = :id_user")
//                .setParameter("id_user", id).uniqueResult();
//    }

}
