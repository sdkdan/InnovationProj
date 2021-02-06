package ru.innovat.dao.authorization;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import ru.innovat.models.authorization.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RoleDao {
    private final SessionFactory sessionFactory;

    @Nullable
    public Role getRoleById(int id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    @Nullable
    public List<String> getRoleNames(int id_user) {
        Session session = sessionFactory.getCurrentSession();
        String role = (String) session.createQuery("SELECT U.roleName FROM Role U, AppUser Au WHERE Au.id_user = " +
                ":id AND Au.role.id_role = U.id_role").setParameter("id", id_user).uniqueResult();
        return new ArrayList<>(Collections.singletonList(role));
    }

    @SuppressWarnings("unchecked")
    @Nullable
    public List<Role> roleList() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Role>) session.createQuery("From Role").list();
    }
}
