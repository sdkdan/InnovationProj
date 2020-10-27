//package ru.innovat.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import ru.innovat.dao.RoleDao;
//import ru.innovat.dao.UserDao;
//import ru.innovat.models.AppUser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final UserDao userDAO;
//
//    private final RoleDao roleDAO;
//
//    public UserDetailsServiceImpl(RoleDao roleDAO, UserDao userDAO) {
//        this.roleDAO = roleDAO;
//        this.userDAO = userDAO;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        AppUser appUser = this.userDAO.findByUsername(userName);
//
//        if (appUser == null) {
//            System.out.println("User not found! " + userName);
//            throw new UsernameNotFoundException("User " + userName + " was not found in the database");
//        }
//
//        List<String> roleNames = this.roleDAO.getRoleNames((int) appUser.getId_user());
//        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
//        if (roleNames != null) {
//            for (String role : roleNames) {
//                GrantedAuthority authority = new SimpleGrantedAuthority(role);
//                grantList.add(authority);
//            }
//        }
//
//        return (UserDetails) new User(appUser.getUsername(), //
//                appUser.getPassword(), grantList);
//    }
//
//}
