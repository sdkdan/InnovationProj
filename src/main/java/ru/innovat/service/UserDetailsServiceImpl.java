//package ru.innovat.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.innovat.dao.RoleDao;
//import ru.innovat.dao.UserDao;
//import ru.innovat.models.AppUser;
//import ru.innovat.models.Role;
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
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;
//    public UserDetailsServiceImpl(RoleDao roleDAO, UserDao userDAO, BCryptPasswordEncoder bCryptPasswordEncoder) {
//        this.roleDAO = roleDAO;
//        this.userDAO = userDAO;
//        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
//    @Transactional
//    public boolean saveUser(AppUser appUser) {
//        AppUser appUserFromDB = userDAO.findByUsername(appUser.getUsername());
//
//        if (appUserFromDB != null) {
//            return false;
//        }
////        Role role = new Role();
////        role.setId_role(2);
////        role.setRoleName("ROLE_USER");
////
////        appUser.setId_role(role);
//        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
//        userDAO.add(appUser);
//        return true;
//    }
//
//    @Transactional
//    public void updateUser(AppUser user) {
//        userDAO.update(user);
//    }
//
//    @Transactional
//    public List<AppUser> userList() {
//        return userDAO.userList();
//    }
//
//    @Transactional
//    public AppUser findUser(int id) {
//        return userDAO.findById(id);
//    }
//
//    @Transactional
//    public List<Role> roleList(){
//        return roleDAO.roleList();
//    }
//
//    @Transactional
//    public Role getRoleById(int id){
//        return roleDAO.getRoleById(id);
//    }
//
//    @Transactional
//    public AppUser setRole(Role role, int id) {
//        AppUser user = userDAO.findById(id);
//        user.setId_role(role);
//        return user;
//    }
//}
