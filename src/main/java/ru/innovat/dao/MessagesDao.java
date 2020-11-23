package ru.innovat.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.innovat.models.Messages;

import java.util.List;

@Repository
public class MessagesDao {

    SessionFactory sessionFactory;


    public MessagesDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(Messages messages) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(messages);
    }

    public void delete(int id) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(id);
    }


    @SuppressWarnings("unchecked")
    public List<Messages> userMessages(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Messages>) session.createQuery("SELECT M FROM Messages M where appUser.id_user =:id")
                .setParameter("id", id).list();
    }
}
