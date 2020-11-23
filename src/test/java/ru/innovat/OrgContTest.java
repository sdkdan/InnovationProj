package ru.innovat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import ru.innovat.models.Organization;
import ru.innovat.service.OrganizationService;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContext.class})
@WebAppConfiguration
public class OrgContTest {

    private MockMvc mockMvc;

    @Autowired
    private OrganizationService todoServiceMock;

    //Add WebApplicationContext field here

    //The setUp() method is omitted.

    @Test
    public void findAll_ShouldAddTodoEntriesToModelAndRenderTodoListView() throws Exception {
        Organization first = new Organization();
        first.setId_organization(2);
        first.setName_organization("Lorem ipsum");
        first.setSite_organization("Foo@ru");


        Organization second = new Organization();
        second.setId_organization(3);
        second.setName_organization("Porem ipsum");
        second.setSite_organization("Fo1o@ru");

        when(todoServiceMock.organizationList()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/organization"))
                .andExpect(status().isOk())
                .andExpect(view().name("organization"))
                .andExpect(forwardedUrl("/organization"))
                .andExpect(model().attribute("organization", hasSize(2)))
                .andExpect(model().attribute("organization", hasItem(
                        allOf(
                                hasProperty("id_organization",  is(2)),
                                hasProperty("name_organization",  is("Lorem ipsum")),
                                hasProperty("site_organization",  is("Foo@ru"))
                        )
                )))
                .andExpect(model().attribute("todos", hasItem(
                        allOf(
                                hasProperty("id_organization", is(3)),
                                hasProperty("name_organization",  is("Porem ipsum")),
                                hasProperty("site_organization",  is("Fo1o@ru"))
                        )
                )));

        verify(todoServiceMock, times(1)).organizationList();
        verifyNoMoreInteractions(todoServiceMock);
    }
}
