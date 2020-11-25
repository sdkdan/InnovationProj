package ru.innovat.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.innovat.controller.major.organization.OrganizationController;
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.OrganizationService;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebMvc
@WithUserDetails("test")
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/sql/create-organization-before.sql","/sql/create-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class OrganizationControllerTest {
    @Autowired
    OrganizationController organizationController;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    OrganizationService organizationService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Test
    public void contextLoads() throws Exception {
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content()
                        .string(containsString("Организации")));
    }

    @Test
    public void organizationPageTest() throws Exception {
        this.mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    public void organizationListTest() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        this.mockMvc.perform(get("/organization"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("/html/body/table/tbody/tr[1]/td[1]").string("Политех"));
    }

    @Test
    public void addNewOrganizationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/organization/add")
                .param("organization", String.valueOf(new Organization()))
                .contentType(MediaType.TEXT_HTML)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    public void deleteOrganization() throws Exception {
        String uri = "/organization/1";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, "Organization is deleted successfully");
    }

    //    @Test
//    public void findAll_ShouldAddOrganizationEntriesToModelAndRenderTodoListView() throws Exception {
//        Organization first = new Organization();
//        first.setId_organization(2);
//        first.setName_organization("Lorem ipsum");
//        first.setSite_organization("Foo@ru");
//
//
//        Organization second = new Organization();
//        second.setId_organization(3);
//        second.setName_organization("Porem ipsum");
//        second.setSite_organization("Fo1o@ru");
//
//        when(organizationService.organizationList()).thenReturn(Arrays.asList(first, second));
//
//        mockMvc.perform(get("/organization"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("organization"))
//                .andExpect(forwardedUrl("/organization"))
//                .andExpect(model().attribute("organization", hasSize(2)))
//                .andExpect(model().attribute("organization", hasItem(
//                        allOf(
//                                hasProperty("id_organization",  is(2)),
//                                hasProperty("name_organization",  is("Lorem ipsum")),
//                                hasProperty("site_organization",  is("Foo@ru"))
//                        )
//                )))
//                .andExpect(model().attribute("todos", hasItem(
//                        allOf(
//                                hasProperty("id_organization", is(3)),
//                                hasProperty("name_organization",  is("Porem ipsum")),
//                                hasProperty("site_organization",  is("Fo1o@ru"))
//                        )
//                )));
//
//        verify(organizationService, times(1)).organizationList();
//        verifyNoMoreInteractions(organizationService);
//    }
//    @Test
//    public void project() throws Exception {
//        this.mockMvc.perform(get("/project"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("projectList", hasSize(1)))
//                .andExpect(model().attribute("projectList", hasItem(
//                        allOf(
//                                hasProperty("name_project", is("База данных инновационных проектов")),
//                                hasProperty("site_project", is("dl.spbstu.ru"))
//                        )
//                )));
//    }


}
