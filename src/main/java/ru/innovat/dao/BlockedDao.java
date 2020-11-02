package ru.innovat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.Blocked;
import ru.innovat.models.VerificationToken;

import java.util.List;

@Repository
public class BlockedDao {
    private SessionFactory sessionFactory;

    public BlockedDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public Blocked findById(int id){
        Session session = sessionFactory.getCurrentSession();
        return session.get(Blocked.class,id);
    }

    public void add(Blocked blocked){
        Session session = sessionFactory.getCurrentSession();
        session.persist(blocked);
    }

    public void update(Blocked blocked){
        Session session = sessionFactory.getCurrentSession();
        session.update(blocked);
    }

    public void delete(Blocked blocked){
        Session session = sessionFactory.getCurrentSession();
        session.delete(blocked);
    }

    @SuppressWarnings("unchecked")
    public List<Blocked> blockedList(){
        Session session = sessionFactory.getCurrentSession();
        return (List<Blocked>) session.createQuery("SELECT * FROM Blocked");
    }

    public Blocked findByUserId(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Blocked blocked = (Blocked) session.createQuery("SELECT B FROM Blocked B WHERE B.appUser.id_user = :id_user").setParameter("id_user", id).uniqueResult();;
        return blocked;
    }

}
