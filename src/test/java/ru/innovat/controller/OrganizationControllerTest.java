package ru.innovat.controller;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
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
    OrganizationService organizationService;
    @Autowired
    OrganizationSearch organizationSearch;

    @Test
    public void getOrganizationsList() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            this.mockMvc.perform(get("/organization"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/organization"))
                    .andExpect(model().attribute("organizationList", hasSize(organizationList.size())))
                    .andExpect(model().attribute("organizationList", hasItem(
                            allOf(
                                    hasProperty("name_organization", is(organizationList
                                            .get(organizationList.size() - 1).getName_organization())),
                                    hasProperty("site_organization", is(organizationList
                                            .get(organizationList.size() - 1).getSite_organization())),
                                    hasProperty("notes_organization", is(organizationList
                                            .get(organizationList.size() - 1).getNotes_organization())
                                    )
                            ))));
        }
    }

    @Test
    public void findByIdOrganization() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int lastIdOrganization = organizationList.get(organizationList.size() - 1).getId_organization();
            mockMvc.perform(get("/organization/{id}", lastIdOrganization))
                    .andExpect(status().isOk())
                    .andExpect(view().name("organization/oneOrg"))
                    .andExpect(model().attribute("organization",
                            allOf(
                                    hasProperty("name_organization", is(organizationList
                                            .get(organizationList.size() - 1).getName_organization())),
                                    hasProperty("site_organization", is(organizationList
                                            .get(organizationList.size() - 1).getSite_organization())),
                                    hasProperty("notes_organization", is(organizationList
                                            .get(organizationList.size() - 1).getNotes_organization())
                                    ))));
        }
    }

    @Test
    public void organizationSearch() throws Exception {
        Organization lastOrganization = organizationService.organizationList().get(organizationService
                .organizationList().size() - 1);
        String lastOrganizationName = lastOrganization.getName_organization();
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
                                hasProperty("name_organization", is(lastFoundedOrganization
                                        .getName_organization())),
                                hasProperty("site_organization", is(lastFoundedOrganization
                                        .getSite_organization())),
                                hasProperty("notes_organization", is(lastFoundedOrganization
                                        .getNotes_organization()))
                        ))));
    }

    @Test
    public void addNewOrganization() throws Exception {
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
    public void organizationEdit() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int lastIdOrganization = organizationList.size();
            mockMvc.perform(post("/organization/{id}/update", lastIdOrganization)
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
    public void deleteOrganization() throws Exception {
        List<Organization> organizationList = organizationService.organizationList();
        if (organizationList.size() > 0) {
            int organizationLastId = organizationList.get(organizationList.size() - 1).getId_organization();
            mockMvc.perform(get("/organization/{id}/delete", organizationLastId))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        }
    }
}