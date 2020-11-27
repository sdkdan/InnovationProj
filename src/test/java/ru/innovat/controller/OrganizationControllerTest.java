package ru.innovat.controller;

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
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.SearchService;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-organization-before.sql", "/sql/create-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "test")
public class OrganizationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    SearchService searchService;

    @Test
    public void organizations() throws Exception {
        this.mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("organization/organization"))
                .andExpect(model().attribute("organizationList", hasSize(2)))
                .andExpect(model().attribute("organizationList", hasItem(
                        allOf(
                                hasProperty("name_organization", is("Политех")),
                                hasProperty("site_organization", is("spbbu@kek.ru")),
                                hasProperty("notes_organization", is("DFDFD"))
                        )
                )));
    }

    @Test
    public void findByIdOrganizationTest() throws Exception {
        mockMvc.perform(get("/organization/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("organization/oneOrganization"))
                .andExpect(model().attribute("organization",
                        hasProperty("name_organization", is("Политех"))))
                .andExpect(model().attribute("organization",
                        hasProperty("site_organization", is("spbbu@kek.ru"))));
    }

    @Test
    public void organizationSearchTest() throws Exception {
        mockMvc.perform(get("/organization?search=Политех"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("organization/organization"))
                .andExpect(model().attribute("organizationList", hasSize(1)))
                .andExpect(model().attribute("organizationList", hasItem(
                        allOf(
                                hasProperty("name_organization", is("Политех")),
                                hasProperty("site_organization", is("spbbu@kek.ru")),
                                hasProperty("notes_organization", is("DFDFD"))
                        )
                )));
    }

    @Test
    public void addNewOrganizationTest() throws Exception {
        mockMvc.perform(post("/organization/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name_organization", "Политех")
                .param("site_organization", "spbbu@kek.ru")
                .param("notes_organization", "DFDFD")
                .param("city_organization", "Nursultan")
                .sessionAttr("organization", new Organization())
        )
                .andExpect(status().is3xxRedirection());
        mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("organization/organization"))
                .andExpect(model().attribute("organizationList", hasSize(2)))
                .andExpect(model().attribute("organizationList", hasItem(
                        allOf(
                                hasProperty("name_organization", is("Политех")),
                                hasProperty("site_organization", is("spbbu@kek.ru")),
                                hasProperty("notes_organization", is("DFDFD"))
                        )
                )));
    }

    @Test
    public void organizationEditTest() throws Exception {
        mockMvc.perform(post("/organization/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name_organization", "Политех1")
                .param("site_organization", "spbbu@kek.ru")
                .param("notes_organization", "DFDFD1")
                .param("city_organization", "Nursultan1")
                .sessionAttr("organization", new Organization())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/organization/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("organization/oneOrganization"))
                .andExpect(model().attribute("organization",
                        hasProperty("name_organization", is("Политех1"))))
                .andExpect(model().attribute("organization",
                        hasProperty("site_organization", is("spbbu@kek.ru"))));
    }

    @Test
    public void deleteOrganizationTest() throws Exception{
        mockMvc.perform(get("/organization/1/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}