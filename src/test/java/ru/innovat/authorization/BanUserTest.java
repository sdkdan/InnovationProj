package ru.innovat.authorization;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BanUserTest extends ConfigAuthorizationTest {

    @Test
    @WithUserDetails(value = "test1")
    public void banUser() throws Exception {
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
    public void unbanUserOnButton() throws Exception {
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
