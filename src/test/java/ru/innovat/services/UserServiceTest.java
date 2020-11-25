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

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"delete-user.sql", "create-user-for-service.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource("../../../resources/application-test.properties")
@Transactional
public class UserServiceTest {
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserService userService;
    @Autowired
    private NewUserService newUserService;

//    @Test
//    public void addUser() {
//        AppUser user = new AppUser();
//
//        user.setEMail("some@mail.ru");
//
//        userDao.add(user);
//
//        Assert.assertNotNull(user.getBlocked());
//        Assert.assertTrue(CoreMatchers.is(user.getRole()).matches(Collections.singleton(Roles.Role_User)));
//
//        Mockito.verify(userDao, Mockito.times(1)).add(user);
//        Mockito.verify(mailSender, Mockito.times(1))
//                .send(
//                        ArgumentMatchers.eq(user.getEMail()),
//                        ArgumentMatchers.anyString(),
//                        ArgumentMatchers.anyString()
//                );
//    }
    @Test
    public void loadByUsernameTest() throws Exception{
        UserDetails username = userService.loadUserByUsername("test");
        assertThat("test").isEqualTo(username.getUsername());
    }

    @Test
    public void userList() throws Exception{
        AppUser appUser = new AppUser();
        Role role = new Role();
        role.setId_role(RolesEnum.Role_User.id_role);
        role.setRoleName(RolesEnum.Role_User.name());
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
        System.out.println("USERS SIZE " + users);
        assertThat(users.size()).isEqualTo(2);
    }

    @Test
    public void findByUsernameTest() throws Exception{
        AppUser appUser = userService.findUserByUsername("test");
        assertThat("test").isEqualTo(appUser.getUsername());
    }

    @Test
    public void findUserByIdTest() throws Exception{
        AppUser appUser = userService.findUserByUsername("test");
        int id = 1;
        assertThat(id).isEqualTo(appUser.getId_user());
    }

    @Test
    public void getRoleByIdTest() throws Exception{
        String role = "ROLE_ADMIN";
        assertThat(role).isEqualTo(userService.getRoleById(1).getRoleName());
    }

    @Test
    public void updateUserTest() throws Exception{
        AppUser appUser = userService.findUser(1);
        Role role = new Role();
        role.setId_role(RolesEnum.Role_User.id_role);
        role.setRoleName(RolesEnum.Role_User.name());
        appUser.setEMail("testEmail");
        appUser.setName("testName");
        userService.updateUser(appUser);
        AppUser updatedUser = userService.findUser(1);
        assertThat(appUser.getName()).isEqualTo("testName");
        assertThat(appUser.getEMail()).isEqualTo("testEmail");
        assertThat(appUser.getUsername()).isEqualTo(updatedUser.getUsername());
    }



}