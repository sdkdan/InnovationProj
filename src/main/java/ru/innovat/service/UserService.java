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
import ru.innovat.dao.UserDao;
import ru.innovat.models.AppUser;
import ru.innovat.models.Person;
import ru.innovat.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    UserDao userDao;
    RoleDao roleDao;
    private AppUser user;


    public UserService(UserDao userDao,@Lazy BCryptPasswordEncoder bCryptPasswordEncoder, RoleDao roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    final
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public UserDetails loadUserByUsername1(String username) throws UsernameNotFoundException {
        AppUser appUser = userDao.findByUsername(username);

        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return appUser;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    @Transactional
      @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       AppUser appUser = this.userDao.findByUsername(userName);

       if (appUser == null) {
         System.out.println("User not found! " + userName);
            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
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


}
