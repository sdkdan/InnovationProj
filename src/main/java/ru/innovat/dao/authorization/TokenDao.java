package ru.innovat.dao.authorization;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.innovat.models.authorization.VerificationToken;

@Repository
@RequiredArgsConstructor
public class TokenDao {

    private final SessionFactory sessionFactory;

    @Nullable
    public  VerificationToken findByToken(String token) {
        Session session = sessionFactory.getCurrentSession();
        return (VerificationToken) session.createQuery("SELECT V FROM VerificationToken V WHERE V.token = :token")
                                          .setParameter("token", token).uniqueResult();
    }

    @Nullable
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
        VerificationToken verificationToken = session.load(VerificationToken.class, id);
        if (verificationToken != null)session.delete(verificationToken);
    }
}
