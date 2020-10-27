package ru.innovat.service;


import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.RoleDao;
<<<<<<< Updated upstream
import ru.innovat.dao.UserDao;
import ru.innovat.models.AppUser;
import ru.innovat.models.Person;
import ru.innovat.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
=======
import ru.innovat.dao.TokenDao;
import ru.innovat.dao.UserDao;
import ru.innovat.models.AppUser;
import ru.innovat.models.VerificationToken;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Calendar;
>>>>>>> Stashed changes
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    UserDao userDao;
<<<<<<< Updated upstream
    RoleDao roleDao;


    public UserService(UserDao userDao,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder, RoleDao roleDao) {
=======
    TokenDao tokenDao;
    final RoleDao roleDao;

    public UserService(UserDao userDao, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder, TokenDao tokenDao, RoleDao roleDao) {
>>>>>>> Stashed changes
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenDao = tokenDao;
        this.roleDao = roleDao;
    }

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.userDao.findByUsername(userName);

        if (appUser == null) {
            System.out.println("Пользователь не найден " + userName);
            throw new UsernameNotFoundException("Пользователь " + userName + " не найдет в базе данных");
        }
        if(!appUser.isEnabled()){
            throw new UsernameNotFoundException("Вы не поддтвердили почту " + userName + " ");
        }
        List<String> roleNames = this.roleDao.getRoleNames((int) appUser.getId_user());
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }

        return (UserDetails) new User(appUser.getUsername(), //
                appUser.getPassword(), grantList);
    }

    @Transactional
    public AppUser findUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userDao.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return appUser;
    }

    @Transactional
    public boolean checkUsername(String username) {
        AppUser appUserFromDB = userDao.findByUsername(username);
        return appUserFromDB != null;
    }

<<<<<<< Updated upstream
        if (appUserFromDB != null) {
            return false;
        }
//        Role role = new Role();
//        role.setId_role(2);
//        role.setRoleName("ROLE_USER");
//
//        appUser.setId_role(role);
=======
    @Transactional
    public boolean checkEmail(String email) {
        AppUser appUserFromDB = userDao.findByUsername(email);
        return appUserFromDB != null;
    }

    @Transactional
    public void saveUser(AppUser appUser) {

>>>>>>> Stashed changes
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        userDao.add(appUser);
    }

    @Transactional
    public boolean deleteUser(int userId) {
        if (userDao.findById(userId) != null) {
            userDao.delete(userId);
            return true;
        }
        return false;
    }

    @Transactional
<<<<<<< Updated upstream
    public void updateUser(AppUser user) {
        userDao.update(user);
    }

    @Transactional
    public List<AppUser> userList() {
        return userDao.userList();
    }

    @Transactional
    public AppUser findUser(int id) {
        return userDao.findById(id);
    }

    @Transactional
    public List<Role> roleList(){
        return roleDao.roleList();
    }

    @Transactional
    public Role getRoleById(int id){
        return roleDao.getRoleById(id);
    }

    @Transactional
    public AppUser setRole(Role role, int id){
        AppUser user =  userDao.findById(id);
        user.setId_role(role);
        return user;
    }


=======
    public void deleteToken(int id){
        tokenDao.delete(id);
    }

    @Transactional
    public void saveToken(VerificationToken verificationToken){
        tokenDao.add(verificationToken);
    }


    @Transactional
    public VerificationToken findByToken(String Token){
        return tokenDao.findByToken(Token);
    }
    @Transactional
    public AppUser getUser(String token) {
        VerificationToken verificationToken = tokenDao.findByToken(token);
        if (token != null) {
            return verificationToken.getUser();
        }
        return null;
    }

    public void update(AppUser appUser){
        userDao.update(appUser);
    }
>>>>>>> Stashed changes
}
