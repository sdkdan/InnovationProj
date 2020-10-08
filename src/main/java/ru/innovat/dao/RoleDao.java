package ru.innovat.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.AppUser;

@Repository
@Transactional
public class RoleDao {


    private final SessionFactory sessionFactory;

    public RoleDao(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public List<String> getRoleNames(int id_user) {
        Session session = this.sessionFactory.getCurrentSession();
        String role = (String) session.createQuery("SELECT U.roleName FROM Role U, AppUser Au WHERE Au.id_user = :id AND Au.id_role.id_role = U.id_role").setParameter("id", id_user).uniqueResult();;
        List<String> appUser = new ArrayList<>();
        appUser.add(role);
        return appUser;
    }

}
