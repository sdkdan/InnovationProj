package ru.innovat.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.innovat.config.ConfigControllerTest;
import ru.innovat.models.major.Project;
import ru.innovat.search.ProjectSearch;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WithMockUser(username = "test", password = "pwd", roles = "ADMIN")
public class ProjectControllerTest extends ConfigControllerTest {

    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectSearch projectSearch;

    @Before
    public void createProjectsForTests(){
        Project newProject1 = Project.builder()
                .nameProject("Разработка нейросети которая улучает качество фото")
                .essenceInnovations("Улучшение качества фото нейросетью")
                .solutionProblems("Плохое качество фотографий на дешевых камерах")
                .levelSolution("Глобальный")
                .competitiveAdvantages("Решение дешевле чем у конкурентов")
                .build();
        Project newProject2 = Project.builder()
                .nameProject("Телефон с зарядкой по FI-WI")
                .essenceInnovations("Зарядка телефонов по FI-WI")
                .solutionProblems("Зарядка телефона по кабелю")
                .levelSolution("Глобальный")
                .competitiveAdvantages("Отсутсвие конкуренции")
                .build();

        projectService.addProject(newProject1);
        projectService.addProject(newProject2);
    }

    @After
    public void deleteProject() {
        for(Project project: projectService.projectList()){
            projectService.deleteProject(project.getId_project());
        }
    }

    @Test
    public void addNewProjectTest() throws Exception {
        List<Project> projectList = projectService.projectList();
        int projectListSize = projectList.size();
        int newAddedProject = 1;
        mockMvc.perform(post("/project/add")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nameProject", "test")
                .param("essenceInnovations", "test")
                .param("solutionProblems", "test")
                .param("levelSolution", "test")
                .param("competitiveAdvantages", "test")
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
    public void projectSearch() throws Exception {
        Project lastProject = projectService.projectList().get(projectService
                .projectList().size() - 1);
        String lastProjectName = lastProject.getNameProject();
        int foundedProjectsListSize = projectSearch.fuzzySearch(lastProjectName).size();
        Project lastFoundedProject = projectSearch.fuzzySearch(lastProjectName).get(projectSearch
                .fuzzySearch(lastProjectName).size() - 1);
        mockMvc.perform(get("/project?search=" + lastProjectName))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("project/project"))
                .andExpect(model().attribute("projectList", hasSize(foundedProjectsListSize)))
                .andExpect(model().attribute("projectList", hasItem(
                        allOf(
                                hasProperty("nameProject", is(lastFoundedProject.getNameProject())),
                                hasProperty("siteProject", is(lastFoundedProject.getSiteProject())),
                                hasProperty("projectDescription", is(lastFoundedProject
                                        .getProjectDescription()))
                        )
                )));
    }

    @Test
    public void projectEditTest() throws Exception {
        List<Project> projectList = projectService.projectList();
        int lastId_project = projectList.get(projectList.size() - 1).getId_project();
        mockMvc.perform(post("/project/{id}/update", lastId_project)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("nameProject", "test1")
                .param("essenceInnovations", "test1")
                .param("solutionProblems", "test1")
                .param("levelSolution", "test1")
                .param("competitiveAdvantages", "test1")
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

    @Test
    public void deleteProjectTest() throws Exception {
        List<Project> projectList = projectService.projectList();
        int projectLastId = projectList.get(projectList.size() - 1).getId_project();
        mockMvc.perform(get("/project/{id}/delete", projectLastId))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}
    