package ru.innovat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.VerificationToken;

import java.util.List;

@Repository
@Transactional
public class TokenDao {

    private SessionFactory sessionFactory;

    public TokenDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public VerificationToken findByToken(String token) {
        Session session = this.sessionFactory.getCurrentSession();
        return (VerificationToken) session.createQuery("SELECT V FROM VerificationToken V WHERE V.token = :token").setParameter("token", token).uniqueResult();

    }

    public VerificationToken findById(int id) {
        return sessionFactory.getCurrentSession().get(VerificationToken.class, id);
    }

    public void add(VerificationToken verificationToken) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(verificationToken);
    }

    public void update(VerificationToken verificationToken) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(verificationToken);
    }

    public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        VerificationToken verificationToken = session.load(VerificationToken.class, id);

        if (verificationToken != null) {
            session.delete(verificationToken);
        }
    }

    @SuppressWarnings("unchecked")
    public List<VerificationToken> VerificationTokenList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<VerificationToken>) session.createQuery("From VerificationToken").list();
    }
}
