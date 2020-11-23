package ru.innovat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.innovat.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("application-test.properties")
@Sql(value = {"create-user-for-ban.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BanUserTest {
    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private UserService userService;

    @PersistenceContext
    private EntityManager entityManager;

    private MockMvc mockMvc;

    @Test
    @WithUserDetails(value = "test1")
    public void banUserTest() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(post("/admin/user/3/saveban")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("comment", "banned test")
                .param("endDate", "2018-11-11"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"));
        mockMvc.perform(get("/admin/user/3"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/oneUser"))
                .andExpect(model().attribute("user", hasProperty("blocked", notNullValue())));
    }

    @Test
    @WithUserDetails(value = "test1")
    public void bannedUserGetPageTest() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/admin/user/3/ban"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("/admin/user/3/ban"));
    }

    @Test
    @WithUserDetails(value = "test1")
    public void unbanUser() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/admin/user/3/unban"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"));
    }

    @Test
    @WithUserDetails(value = "test1")
    public void unbanUserTestOnButton() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        mockMvc.perform(get("/admin/user/1/unban"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:"));
        mockMvc.perform(get("/admin/user/1"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("user/oneUser"))
                .andExpect(model().attribute("user", hasProperty("blocked", nullValue())));
    }

}
