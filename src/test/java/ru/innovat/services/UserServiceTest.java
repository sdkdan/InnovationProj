package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
//import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.authorization.UserDao;
import ru.innovat.models.authorization.AppUser;
//import ru.innovat.models.Roles;
import ru.innovat.models.authorization.Role;
import ru.innovat.models.utils.RolesEnum;
import ru.innovat.service.authorization.NewUserService;
import ru.innovat.service.authorization.UserService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class UserServiceTest extends ConfigServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private NewUserService newUserService;

    @Test
    public void loadByUsername() {
        UserDetails username = userService.loadUserByUsername("test");
        assertThat("test").isEqualTo(username.getUsername());
    }

    @Test
    public void getUserList() {
        AppUser appUser = new AppUser();
        Role role = new Role();
        role.setId_role(RolesEnum.ROLE_USER.id_role);
        role.setRoleName(RolesEnum.ROLE_USER.name());

        appUser.setUsername("test11");
        appUser.setLastName("test");
        appUser.setName("test");
        appUser.setPassword("wer");
        appUser.setPasswordConfirm("wer");
        appUser.setEMail("testtttt@ru");
        appUser.setRole(role);
        appUser.setEnabled(true);

        AppUser appUser2 = new AppUser();
        appUser2.setUsername("test2");
        appUser2.setLastName("test");
        appUser2.setName("test");
        appUser2.setPassword("wer");
        appUser2.setPasswordConfirm("wer");
        appUser2.setEMail("testtttt@ru");
        appUser2.setRole(role);
        appUser2.setEnabled(true);

        newUserService.saveUser(appUser);
        newUserService.saveUser(appUser2);
        List<AppUser> users = userService.userList();
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void findByUsername() {
        AppUser appUser = userService.findUserByUsername("test");
        assertThat("test").isEqualTo(appUser.getUsername());
    }

    @Test
    public void findUserById() {
        AppUser appUser = userService.findUserByUsername("test");
        int id = 1;
        assertThat(id).isEqualTo(appUser.getId_user());
    }

    @Test
    public void getRoleById() {
        String role = "ROLE_ADMIN";
        assertThat(role).isEqualTo(userService.getRoleById(1).getRoleName());
    }

    @Test
    public void updateUser() {
        AppUser appUser = userService.findUser(1);
        Role role = new Role();
        role.setId_role(RolesEnum.ROLE_USER.id_role);
        role.setRoleName(RolesEnum.ROLE_USER.name());
        appUser.setEMail("testEmail");
        appUser.setName("testName");
        userService.updateUser(appUser);
        AppUser updatedUser = userService.findUser(1);
        assertThat(appUser.getName()).isEqualTo("testName");
        assertThat(appUser.getEMail()).isEqualTo("testEmail");
        assertThat(appUser.getUsername()).isEqualTo(updatedUser.getUsername());
    }

}
