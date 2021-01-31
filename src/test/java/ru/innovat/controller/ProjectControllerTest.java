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
import ru.innovat.models.major.Person;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.ProjectService;
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
public class ProjectControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ProjectService projectService;
    @Autowired
    SearchService searchService;

    @Test
    public void projects() throws Exception {
        List<Project> projectList = projectService.projectList();
        if (projectList.size() > 0) {
            this.mockMvc.perform(get("/project"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(view().name("project/project"))
                    .andExpect(model().attribute("projectList", hasSize(projectList.size())))
                    .andExpect(model().attribute("projectList", hasItem(
                            allOf(
                                    hasProperty("name_project", is(projectList.get(projectList.size()-1).getName_project())),
                                    hasProperty("site_project", is(projectList.get(projectList.size()-1).getSite_project())),
                                    hasProperty("project_description", is(projectList.get(projectList.size()-1).getProject_description()))
                            ))));
        }
    }

    @Test
    public void findByIdProjectTest() throws Exception {
        List<Project> projectList = projectService.projectList();
        if (projectList.size() > 0) {
            int lastIdProject = projectList.get(projectList.size()-1).getId_project();
            mockMvc.perform(get("/project/{id}", lastIdProject))
                    .andExpect(status().isOk())
                    .andExpect(view().name("project/oneProject"))
                    .andExpect(model().attribute("project",
                            allOf(
                                    hasProperty("name_project", is(projectList.get(projectList.size()-1).getName_project())),
                                    hasProperty("site_project", is(projectList.get(projectList.size()-1).getSite_project())),
                                    hasProperty("project_description", is(projectList.get(projectList.size()-1).getProject_description()))
                            )));
        }
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
        List<Project> projectList = projectService.projectList();
        int projectListSize = projectList.size();
        int newAddedProject = 1;
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
                .andExpect(model().attribute("projectList", hasSize(projectListSize + newAddedProject)))
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
        List<Project> projectList = projectService.projectList();
        if (projectList.size() > 0) {
            int lastIdProject = projectList.get(projectList.size() - 1).getId_project();
            mockMvc.perform(post("/project/{id}/update",lastIdProject)
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

            mockMvc.perform(get("/project/{id}", lastIdProject))
                    .andExpect(status().isOk())
                    .andExpect(view().name("project/oneProject"))
                    .andExpect(model().attribute("project",
                            hasProperty("name_project", is("test1"))))
                    .andExpect(model().attribute("project",
                            hasProperty("site_project", is("test1"))));
        }
    }

    @Test
    public void deleteProjectTest() throws Exception{
        List<Project> projectList = projectService.projectList();
        if (projectList.size() > 0) {
            int projectLastId = projectList.get(projectList.size()-1).getId_project();
            mockMvc.perform(get("/project/{id}/delete", projectLastId))
                    .andDo(print())
                    .andExpect(status().is3xxRedirection());
        }
    }

}