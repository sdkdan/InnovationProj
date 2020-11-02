package ru.innovat.service;


import org.springframework.beans.factory.annotation.Autowired;
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
import ru.innovat.dao.*;
import ru.innovat.models.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ru.innovat.dao.UserDao;
import ru.innovat.models.AppUser;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;


@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    UserDao userDao;
    RoleDao roleDao;
    private AppUser user;
    TokenDao tokenDao;
    BlockedDao blockedDao;


    public UserService(UserDao userDao, @Lazy BCryptPasswordEncoder bCryptPasswordEncoder, TokenDao tokenDao, RoleDao roleDao, BlockedDao blockedDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenDao = tokenDao;
        this.blockedDao = blockedDao;
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
        if(!appUser.isAccountNonLocked()){
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
            try {
                Date date = formatter.parse(blockedDao.findById(appUser.getBlocked().getId_blocked()).getEndDate());
                if(!(date.compareTo(new Date()) <0)) throw new UsernameNotFoundException("Все ясно бан " + userName + " ");
                appUser.setBlocked(null);
                userDao.update(appUser);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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


    @Transactional
    public boolean checkEmail(String email) {
        AppUser appUserFromDB = userDao.findByUsername(email);
        return appUserFromDB != null;
    }

    @Transactional
    public void saveUser(AppUser appUser) {

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        userDao.add(appUser);
    }



    @Transactional
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
        user.setRole(role);
        return user;
    }



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

    @Transactional
    public void update(AppUser appUser){
        userDao.update(appUser);
    }

    @Transactional
    public void addBlocked(Blocked blocked){
        blockedDao.add(blocked);
    }

    @Transactional
    public Blocked getBlocked(int id){
        return blockedDao.findById(id);
    }
}
