package ru.innovat.service;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.UserDao;
import ru.innovat.models.AppUser;
import ru.innovat.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    UserDao userDao;


    public UserService(UserDao userDao,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDao = userDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userDao.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return appUser;
    }

    @Transactional
    public boolean saveUser(AppUser appUser) {
        AppUser appUserFromDB = userDao.findByUsername(appUser.getUsername());

        if (appUserFromDB != null) {
            return false;
        }
        Role role = new Role();
        role.setId_role(2);
        role.setRoleName("ROLE_USER");

        appUser.setId_role(role);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        userDao.add(appUser);
        return true;
    }

    @Transactional
    public boolean deleteUser(int userId) {
        if (userDao.findById(userId) != null) {
            userDao.delete(userId);
            return true;
        }
        return false;
    }

}
