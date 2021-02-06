package ru.innovat.authorization;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.innovat.models.authorization.AppUser;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class LoginTest extends ConfigAuthorizationTest {
    private String token = "testtoken";

    @Test
    public void registrationConfirm() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        ResultActions resultActions = this.mockMvc.perform(get("/confirm-account?token=" + token));
        resultActions.andExpect(status().is2xxSuccessful());
        resultActions.andExpect(model().attribute("message", "Почта подтвержденна"));
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "ADMIN")
    public void checkAccessWithRole() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void checkAccessWithoutLogin() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/admin"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/login"));
        ;
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void checkAccessWithRoleUser() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "test", password = "pwd", roles = "USER")
    public void checkAccessToSupport() throws Exception {
        mockMvc.perform(get("/admin"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void registerNewUserUrlTest() throws Exception {
        mockMvc.perform(get("/register"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void registerNewUser() throws Exception {
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name", "test")
                .param("username", "test111")
                .param("lastName", "test")
                .param("password", "111")
                .param("passwordConfirm", "111")
                .param("eMail", "test@df.ru")
                .sessionAttr("userForm", new AppUser())
        )
                .andExpect(status().is2xxSuccessful())
                .andExpect(redirectedUrl("registration/successfulRegistration"));
    }

    @Test
    @WithUserDetails(value = "test1")
    public void checkDataOnMyProfile() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/myprofile"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/myprofile"))
                .andExpect(model().attribute("user",
                        hasProperty("username", is("test1"))));
    }


}
