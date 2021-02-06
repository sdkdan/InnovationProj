package ru.innovat.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.innovat.models.major.Organization;
import ru.innovat.search.OrganizationSearch;
import ru.innovat.service.major.OrganizationService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WithMockUser(username = "test", password = "pwd", roles = "ADMIN")
public class OrganizationControllerTest extends ConfigControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    OrganizationSearch organizationSearch;

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
                                    hasProperty("nameOrganization", is(organizationList
                                            .get(organizationList.size() - 1).getNameOrganization())),
                                    hasProperty("siteOrganization", is(organizationList
                                            .get(organizationList.size() - 1).getSiteOrganization())),
                                    hasProperty("notesOrganization", is(organizationList
                                            .get(organizationList.size() - 1).getNotesOrganization())
                                    )
                            ))));
        }
    }

    @Test
    public void findById_organizationTest() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int lastId_organization = organizationList.get(organizationList.size() - 1).getId_organization();
            mockMvc.perform(get("/organization/{id}", lastId_organization))
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/oneOrg"))
                    .andExpect(model().attribute("organization",
                            allOf(
                                    hasProperty("nameOrganization", is(organizationList
                                            .get(organizationList.size() - 1).getNameOrganization())),
                                    hasProperty("siteOrganization", is(organizationList
                                            .get(organizationList.size() - 1).getSiteOrganization())),
                                    hasProperty("notesOrganization", is(organizationList
                                            .get(organizationList.size() - 1).getNotesOrganization())
                                    ))));
        }
    }

    @Test
    public void organizationSearch() throws Exception {
        Organization lastOrganization = organizationService.organizationList().get(organizationService
                .organizationList().size() - 1);
        String lastOrganizationName = lastOrganization.getNameOrganization();
        int foundedOrganizationsListSize = organizationSearch.fuzzySearch(lastOrganizationName).size();
        Organization lastFoundedOrganization = organizationSearch.fuzzySearch(lastOrganizationName).get(organizationSearch
                .fuzzySearch(lastOrganizationName).size() - 1);
        mockMvc.perform(get("/organization?search=" + lastOrganizationName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("organization/organization"))
                .andExpect(model().attribute("organizationList", hasSize(foundedOrganizationsListSize)))
                .andExpect(model().attribute("organizationList", hasItem(
                        allOf(
                                hasProperty("nameOrganization", is(lastFoundedOrganization
                                        .getNameOrganization())),
                                hasProperty("siteOrganization", is(lastFoundedOrganization
                                        .getSiteOrganization())),
                                hasProperty("notesOrganization", is(lastFoundedOrganization
                                        .getNotesOrganization()))
                        ))));
    }

    @Test
    public void addNewOrganizationTest() throws Exception {
        int organizationListSize = organizationService.organizationList().size();
        int newAddedOrganization = 1;
        mockMvc.perform(post("/organization/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nameOrganization", "Политех")
                .param("siteOrganization", "https://www.spbstu.ru/")
                .param("notesOrganization", "university in spb")
                .param("cityOrganization", "Saint-Petersburg")
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
                                hasProperty("nameOrganization", is("Политех")),
                                hasProperty("siteOrganization", is("https://www.spbstu.ru/")),
                                hasProperty("notesOrganization", is("university in spb"))
                        ))));
    }

    @Test
    public void organizationEditTest() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int lastId_organization = organizationList.size();
            mockMvc.perform(post("/organization/{id}/update", lastId_organization)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("nameOrganization", "Политех Петра")
                    .param("siteOrganization", "https://www.spbstu.ru/")
                    .param("notesOrganization", "university in spb")
                    .param("cityOrganization", "Saint-Petersburg")
                    .sessionAttr("organization", new Organization())
            )
                    .andExpect(status().is3xxRedirection());

            mockMvc.perform(get("/organization/{id}", lastId_organization))
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/oneOrg"))
                    .andExpect(model().attribute("organization",
                            allOf(
                                    hasProperty("nameOrganization", is("Политех Петра")),
                                    hasProperty("siteOrganization", is("https://www.spbstu.ru/")),
                                    hasProperty("notesOrganization", is("university in spb"))
                            )));
        }
    }

    @Test
    public void deleteOrganizationTest() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int organizationLastId = organizationList.get(organizationList.size() - 1).getId_organization();
            mockMvc.perform(get("/organization/{id}/delete", organizationLastId))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        }
    }
}