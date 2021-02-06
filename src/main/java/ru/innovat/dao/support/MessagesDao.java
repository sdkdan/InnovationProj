package ru.innovat.dao.support;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.support.Messages;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MessagesDao {
    private final SessionFactory sessionFactory;

    public void add(Messages messages) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(messages);
    }

    @SuppressWarnings("unchecked")
    public List<Messages> userMessages(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Messages>) session.createQuery("SELECT M FROM Messages M where appUser.id_user =:id")
                .setParameter("id", id).list();
    }
}
