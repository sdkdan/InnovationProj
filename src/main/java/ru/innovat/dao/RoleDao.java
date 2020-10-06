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
import ru.innovat.models.UserRole;

@Repository
@Transactional
public class RoleDao {

    //private final EntityManager entityManager;
    private SessionFactory sessionFactory;

    public RoleDao(EntityManager entityManager, SessionFactory sessionFactory) {
        //this.entityManager = entityManager;
        this.sessionFactory = sessionFactory;
    }

//    public List<String> getRoleNames(Long id_user) {
//        String sql = "Select ur.appRole.roleName from " + UserRole.class.getName() + " ur " //
//                + " where ur.appUser.id_user = :id_user ";
//
//        Query query = this.entityManager.createQuery(sql, String.class);
//        query.setParameter("id_user", id_user);
//        return query.getResultList();
//    }

    public List<String> getRoleNames(int id_user) {
        Session session = this.sessionFactory.getCurrentSession();
//        List<String> appUser = new ArrayList<>();
//        appUser.add("ROLE_ADMIN");
        String role = (String) session.createQuery("SELECT U.roleName FROM Role U, UserRole Ur WHERE Ur.id_user.id_user = :id AND Ur.id_role = U.id_role").setParameter("id", id_user).uniqueResult();;
        List<String> appUser = new ArrayList<>();
        appUser.add(role);
        //"SELECT U.roleName FROM Role U, UserRole Ur WHERE Ur.ud_user=id_user AND Ur.id_role=U.id_role"
        return appUser;
    }

}
