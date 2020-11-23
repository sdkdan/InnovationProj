package ru.innovat.service;


import lombok.AllArgsConstructor;
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
import ru.innovat.dao.BlockedDao;
import ru.innovat.dao.RoleDao;
import ru.innovat.dao.UserDao;
import ru.innovat.models.AppUser;
import ru.innovat.models.Blocked;
import ru.innovat.models.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    final UserDao userDao;
    final RoleDao roleDao;
    final BlockedDao blockedDao;
    final EmailService emailService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = this.userDao.findByUsername(userName);
        if (appUser == null) {
            System.out.println("Пользователь не найден " + userName);
            throw new UsernameNotFoundException("Пользователь " + userName + " не найдет в базе данных");
        }
        if (!appUser.isEnabled()) {
            throw new UsernameNotFoundException("Вы не поддтвердили почту " + userName + " ");
        }
        if (!appUser.isAccountNonLocked()) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd", Locale.ENGLISH);
            try {
                Date date = formatter.parse(blockedDao.findById(appUser.getBlocked().getId_blocked()).getEndDate());
                if (!(date.compareTo(new Date()) < 0))
                    throw new UsernameNotFoundException("Все ясно бан " + userName + " ");
                appUser.setBlocked(null);
                userDao.update(appUser);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        List<String> roleNames = this.roleDao.getRoleNames(appUser.getId_user());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNames != null) {
            for (String role : roleNames) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role);
                grantList.add(authority);
            }
        }
        return new User(appUser.getUsername(), appUser.getPassword(), grantList);
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
    public List<Role> roleList() {
        return roleDao.roleList();
    }

    @Transactional
    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    @Transactional
    public AppUser setRole(Role role, int id) {
        AppUser user = userDao.findById(id);
        user.setRole(role);
        return user;
    }

    @Transactional
    public void update(AppUser appUser) {
        userDao.update(appUser);
    }

    @Transactional
    public void addBlocked(Blocked blocked) {
        blockedDao.add(blocked);
    }

    @Transactional
    public Blocked getBlocked(int id) {
        return blockedDao.findById(id);
    }

    @Transactional
    public List<AppUser> roleUserList() {
        return userDao.roleUserList();
    }

}