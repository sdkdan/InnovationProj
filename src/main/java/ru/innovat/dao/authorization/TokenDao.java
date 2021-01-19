package ru.innovat.dao.authorization;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.authorization.VerificationToken;

import java.util.List;

@Repository
@AllArgsConstructor
public class TokenDao {
    private final SessionFactory sessionFactory;

    public VerificationToken findByToken(String token) {
        Session session = sessionFactory.getCurrentSession();
        return (VerificationToken) session.createQuery("SELECT V FROM VerificationToken V WHERE V.token = :token")
                .setParameter("token", token).uniqueResult();
    }

    public VerificationToken findById(int id) {
        return sessionFactory.getCurrentSession().get(VerificationToken.class, id);
    }

    public void add(VerificationToken verificationToken) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(verificationToken);
    }

    public void update(VerificationToken verificationToken) {
        Session session = sessionFactory.getCurrentSession();
        session.update(verificationToken);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(id);
    }

    @SuppressWarnings("unchecked")
    public List<VerificationToken> verificationTokenList() {
        Session session = this.sessionFactory.getCurrentSession();
        return (List<VerificationToken>) session.createQuery("From VerificationToken").list();
    }
}
