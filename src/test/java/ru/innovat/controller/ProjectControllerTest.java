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
import ru.innovat.models.major.Project;
import ru.innovat.service.major.ProjectService;
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
@Sql(value = {"/sql/create-project-before.sql", "/sql/create-user-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@WithUserDetails(value = "test")
public class ProjectControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProjectService projectService;
    @Autowired
    SearchService searchService;

    @Test
    public void projects() throws Exception {
        this.mockMvc.perform(get("/project"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("project/project"))
                .andExpect(model().attribute("projectList", hasSize(2)))
                .andExpect(model().attribute("projectList", hasItem(
                        allOf(
                                hasProperty("name_project", is("База данных инновационных проектов")),
                                hasProperty("site_project", is("dl.spbstu.ru")),
                                hasProperty("project_description", is("Описание проекта:..."))
                        )
                )));
    }

    @Test
    public void findByIdProjectTest() throws Exception {
        mockMvc.perform(get("/project/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("project/oneProject"))
                .andExpect(model().attribute("project",
                        hasProperty("name_project", is("База данных инновационных проектов"))))
                .andExpect(model().attribute("project",
                        hasProperty("site_project", is("dl.spbstu.ru"))));
    }

    @Test
    public void projectSearchTest() throws Exception {
        mockMvc.perform(get("/project?search=Базы"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("project/project"))
                .andExpect(model().attribute("projectList", hasSize(1)))
                .andExpect(model().attribute("projectList", hasItem(
                        allOf(
                                hasProperty("name_project", is("База данных инновационных проектов")),
                                hasProperty("site_project", is("dl.spbstu.ru")),
                                hasProperty("project_description", is("Описание проекта:..."))
                        )
                )));
    }

    @Test
    public void addNewProjectTest() throws Exception {
        mockMvc.perform(post("/project/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name_project", "test")
                .param("essence_innovations", "test")
                .param("solution_problems", "test")
                .param("level_solution", "test")
                .param("competitive_advantages", "test")
                .param("start_date", "2020-10-20")
                .param("current_stage", "test")
                .param("expertise_project", "test")
                .param("project_description", "test")
                .param("site_project", "test")
                .param("number_Phone_project", "test")
                .sessionAttr("project", new Project())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/project"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("project/project"))
                .andExpect(model().attribute("projectList", hasSize(3)))
                .andExpect(model().attribute("projectList", hasItem(
                        allOf(
                                hasProperty("name_project", is("test")),
                                hasProperty("site_project", is("test")),
                                hasProperty("project_description", is("test"))
                        )
                )));
    }

    @Test
    public void projectEditTest() throws Exception {
       mockMvc.perform(post("/project/1/update")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("name_project", "test1")
                .param("essence_innovations", "test1")
                .param("solution_problems", "test1")
                .param("level_solution", "test1")
                .param("competitive_advantages", "test1")
                .param("start_date", "2020-10-20")
                .param("current_stage", "test1")
                .param("expertise_project", "test1")
                .param("project_description", "test1")
                .param("site_project", "test1")
                .param("number_Phone_project", "test1")
                .sessionAttr("project", new Project())
        )
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/project/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(view().name("project/oneProject"))
                .andExpect(model().attribute("project",
                        hasProperty("name_project", is("test1"))))
                .andExpect(model().attribute("project",
                        hasProperty("site_project", is("test1"))));
    }

    @Test
    public void deleteProjectTest() throws Exception{
        mockMvc.perform(get("/project/1/delete"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }

}