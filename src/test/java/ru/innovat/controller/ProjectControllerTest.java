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
                                    hasProperty("nameProject", is(projectList.get(projectList.size()-1).getNameProject())),
                                    hasProperty("siteProject", is(projectList.get(projectList.size()-1).getSiteProject())),
                                    hasProperty("projectDescription", is(projectList.get(projectList.size()-1).getProjectDescription()))
                            ))));
        }
    }

    @Test
    public void findById_projectTest() throws Exception {
        List<Project> projectList = projectService.projectList();
        if (projectList.size() > 0) {
            int lastId_project = projectList.get(projectList.size()-1).getId_project();
            mockMvc.perform(get("/project/{id}", lastId_project))
                    .andExpect(status().isOk())
                    .andExpect(view().name("project/oneProject"))
                    .andExpect(model().attribute("project",
                            allOf(
                                    hasProperty("nameProject", is(projectList.get(projectList.size()-1).getNameProject())),
                                    hasProperty("siteProject", is(projectList.get(projectList.size()-1).getSiteProject())),
                                    hasProperty("projectDescription", is(projectList.get(projectList.size()-1).getProjectDescription()))
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
                                hasProperty("nameProject", is("База данных инновационных проектов")),
                                hasProperty("siteProject", is("dl.spbstu.ru")),
                                hasProperty("projectDescription", is("Описание проекта:..."))
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
                .param("nameProject", "test")
                .param("essence_innovations", "test")
                .param("solutionProblems", "test")
                .param("levelSolution", "test")
                .param("competitive_advantages", "test")
                .param("startDate", "2020-10-20")
                .param("currentStage", "test")
                .param("expertiseProject", "test")
                .param("projectDescription", "test")
                .param("siteProject", "test")
                .param("numberPhoneProject", "test")
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
                                hasProperty("nameProject", is("test")),
                                hasProperty("siteProject", is("test")),
                                hasProperty("projectDescription", is("test"))
                        )
                )));
    }

    @Test
    public void projectEditTest() throws Exception {
        List<Project> projectList = projectService.projectList();
        if (projectList.size() > 0) {
            int lastId_project = projectList.get(projectList.size() - 1).getId_project();
            mockMvc.perform(post("/project/{id}/update",lastId_project)
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("nameProject", "test1")
                    .param("essence_innovations", "test1")
                    .param("solutionProblems", "test1")
                    .param("levelSolution", "test1")
                    .param("competitive_advantages", "test1")
                    .param("startDate", "2020-10-20")
                    .param("currentStage", "test1")
                    .param("expertiseProject", "test1")
                    .param("projectDescription", "test1")
                    .param("siteProject", "test1")
                    .param("numberPhoneProject", "test1")
                    .sessionAttr("project", new Project())
            )
                    .andExpect(status().is3xxRedirection());

            mockMvc.perform(get("/project/{id}", lastId_project))
                    .andExpect(status().isOk())
                    .andExpect(view().name("project/oneProject"))
                    .andExpect(model().attribute("project",
                            hasProperty("nameProject", is("test1"))))
                    .andExpect(model().attribute("project",
                            hasProperty("siteProject", is("test1"))));
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