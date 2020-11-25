package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.authorization.AppUser;
import ru.innovat.models.authorization.Role;
import ru.innovat.models.utils.RolesEnum;
import ru.innovat.models.authorization.VerificationToken;
import ru.innovat.service.authorization.NewUserService;
import ru.innovat.service.authorization.UserService;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("../../../../resources/application-test.properties")
@Sql(value = {"create-user-for-service.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Transactional
public class NewUserServiceTest {

    @Autowired
    NewUserService newUserService;
    @Autowired
    EntityManager entityManager;
    @Autowired
    UserService userService;

    @Test
    public void testFindTokenWithBD() throws Exception {
        AppUser appUser = new AppUser();
        appUser.setId_user(1);
        VerificationToken token = new VerificationToken("testToken", appUser);
        entityManager.persist(token);
        entityManager.flush();
        VerificationToken foundToken = newUserService.findByToken(token.getToken());
        assertThat(foundToken.getToken())
                .isEqualTo(token.getToken());
    }

    @Test
    public void saveUserTest() throws Exception{
        AppUser appUser = new AppUser();
        Role role = new Role();
        role.setId_role(RolesEnum.Role_User.id_role);
        role.setRoleName(RolesEnum.Role_User.name());
        appUser.setUsername("test4");
        appUser.setLastName("test");
        appUser.setName("test");
        appUser.setPassword("wer");
        appUser.setPasswordConfirm("wer");
        appUser.setEMail("testtttt@ru");
        appUser.setRole(role);
        appUser.setEnabled(true);
        newUserService.saveUser(appUser);
        AppUser savedUser = userService.findUserByUsername("test4");
        assertThat(appUser.getUsername()).isEqualTo(savedUser.getUsername());
        assertThat(appUser.getName()).isEqualTo(savedUser.getName());
    }

    @Test
    public void emailVerification()  throws Exception{
        AppUser appUser = new AppUser();
        Role role = new Role();
        role.setId_role(RolesEnum.Role_User.id_role);
        role.setRoleName(RolesEnum.Role_User.name());
        appUser.setUsername("test5");
        appUser.setLastName("test");
        appUser.setName("test");
        appUser.setPassword("wer");
        appUser.setPasswordConfirm("wer");
        appUser.setEMail("testtttt@ru");
        appUser.setRole(role);
        appUser.setEnabled(true);
        newUserService.saveUser(appUser);
        newUserService.saveToken(appUser);
        String returned = newUserService.emailVerification("token");
        assertThat(returned).isEqualTo("Данная ссылка не действительна либо сломана");
    }

    @Test
    public void findByToken() throws Exception{
        String token = "testToken";
        VerificationToken verificationToken = newUserService.findByToken(token);
        assertThat(token).isEqualTo(verificationToken.getToken());
    }






}
