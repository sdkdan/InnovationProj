package ru.innovat.service.authorization;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.authorization.BlockedDao;
import ru.innovat.dao.authorization.RoleDao;
import ru.innovat.dao.authorization.UserDao;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.authorization.Blocked;
import ru.innovat.models.authorization.Role;
import ru.innovat.service.utils.DateExpired;
import ru.innovat.service.utils.DateFormatConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserDao userDao;
    private final RoleDao roleDao;
    private final BlockedDao blockedDao;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = userDao.findByUsername(userName);
        if (appUser == null) {
            throw new UsernameNotFoundException("Пользователь " + userName + " не найдет в базе данных");
        } else {
            if (!appUser.isEnabled()) {
                throw new UsernameNotFoundException("Вы не поддтвердили почту " + userName + " ");
            } else {
                if (!appUser.isAccountNonLocked()) {
                    SimpleDateFormat formatter = DateFormatConfig.dateFormat();
                    try {
                        Date banDate = formatter.parse(blockedDao.findById(appUser.getBlocked().getId_blocked()).getEndDate());
                        if (!DateExpired.isExpired(banDate)) {
                            throw new UsernameNotFoundException("Все ясно бан " + userName + " ");
                        } else {
                            appUser.setBlocked(null);
                            userDao.update(appUser);
                        }
                    } catch (ParseException e) {
                        log.error("login exception");
                    }
                }
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

    public AppUser findUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userDao.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return appUser;
    }

    public void updateUser(AppUser user) {
        userDao.update(user);
    }

    public List<AppUser> userList() {
        return userDao.userList();
    }

    public AppUser findUser(int id) {
        return userDao.findById(id);
    }

    public List<Role> roleList() {
        return roleDao.roleList();
    }

    public Role getRoleById(int id) {
        return roleDao.getRoleById(id);
    }

    public AppUser setRole(Role role, int id) {
        AppUser user = userDao.findById(id);
        if (user == null) {
            return null;
        }
        user.setRole(role);
        return user;
    }

    public void update(AppUser appUser) {
        userDao.update(appUser);
    }

    public void addBlocked(Blocked blocked) {
        blockedDao.add(blocked);
    }

    public Blocked getBlocked(int id) {
        return blockedDao.findById(id);
    }

    public List<AppUser> roleUserList() {
        return userDao.roleUserList();
    }
}