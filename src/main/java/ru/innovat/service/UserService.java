package ru.innovat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.UserDao;
import ru.innovat.models.User;

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
        User user = userDao.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    @Transactional
    public User findUserById(int userId) {
        Optional<User> userFromDb = Optional.ofNullable(userDao.findById(userId));
        return userFromDb.orElse(new User());
    }

    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = userDao.findByUsername(user.getUsername());

        if (userFromDB != null) {
            return false;
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userDao.add(user);
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
