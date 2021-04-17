package ru.innovat.dao.authorization;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.innovat.models.authorization.Blocked;

@Repository
@RequiredArgsConstructor
public class BlockedDao {

    private final SessionFactory sessionFactory;

    @Nullable
    public Blocked findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Blocked.class, id);
    }

    public void add(Blocked blocked) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(blocked);
    }

}
