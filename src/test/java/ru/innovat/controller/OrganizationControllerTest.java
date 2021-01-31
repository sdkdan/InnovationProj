package ru.innovat.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.innovat.models.major.Event;
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.SearchService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "test", password = "pwd", roles = "ADMIN")
public class OrganizationControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    SearchService searchService;

    @Test
    public void organizations() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            this.mockMvc.perform(get("/organization"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/organization"))
                    .andExpect(model().attribute("organizationList", hasSize(organizationList.size())))
                    .andExpect(model().attribute("organizationList", hasItem(
                            allOf(
                                    hasProperty("name_organization", is(organizationList.get(organizationList.size()-1).getName_organization())),
                                    hasProperty("site_organization", is(organizationList.get(organizationList.size()-1).getSite_organization())),
                                    hasProperty("notes_organization", is(organizationList.get(organizationList.size()-1).getNotes_organization())
                            )
                    ))));
        }
    }

    @Test
    public void findByIdOrganizationTest() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int lastIdOrganization = organizationList.get(organizationList.size()-1).getId_organization();
            mockMvc.perform(get("/organization/{id}", lastIdOrganization))
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/oneOrg"))
                    .andExpect(model().attribute("organization",
                            allOf(
                                    hasProperty("name_organization", is(organizationList.get(organizationList.size()-1).getName_organization())),
                                    hasProperty("site_organization", is(organizationList.get(organizationList.size()-1).getSite_organization())),
                                    hasProperty("notes_organization", is(organizationList.get(organizationList.size()-1).getNotes_organization())
                                    ))));
        }
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
                            ))));
        }

    @Test
    public void addNewOrganizationTest() throws Exception {
        int organizationListSize = organizationService.organizationList().size();
        int newAddedOrganization = 1;
        mockMvc.perform(post("/organization/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name_organization", "Политех")
                .param("site_organization", "https://www.spbstu.ru/")
                .param("notes_organization", "university in spb")
                .param("city_organization", "Saint-Petersburg")
                .sessionAttr("organization", new Organization())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("organization/organization"))
                .andExpect(model().attribute("organizationList", hasSize(organizationListSize + newAddedOrganization)))
                .andExpect(model().attribute("organizationList", hasItem(
                        allOf(
                                hasProperty("name_organization", is("Политех")),
                                hasProperty("site_organization", is("https://www.spbstu.ru/")),
                                hasProperty("notes_organization", is("university in spb"))
                        ))));
    }

    @Test
    public void organizationEditTest() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int lastIdOrganization = organizationList.size();
            mockMvc.perform(post("/organization/{id}/update",lastIdOrganization)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("name_organization", "Политех Петра")
                    .param("site_organization", "https://www.spbstu.ru/")
                    .param("notes_organization", "university in spb")
                    .param("city_organization", "Saint-Petersburg")
                    .sessionAttr("organization", new Organization())
            )
                    .andExpect(status().is3xxRedirection());

            mockMvc.perform(get("/organization/{id}", lastIdOrganization))
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/oneOrg"))
                    .andExpect(model().attribute("organization",
                            allOf(
                                    hasProperty("name_organization", is("Политех Петра")),
                                    hasProperty("site_organization", is("https://www.spbstu.ru/")),
                                    hasProperty("notes_organization", is("university in spb"))
                            )));
        }
    }

    @Test
    public void deleteOrganizationTest() throws Exception{
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int organizationLastId = organizationList.get(organizationList.size()-1).getId_organization();
            mockMvc.perform(get("/organization/{id}/delete", organizationLastId))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        }
    }
}