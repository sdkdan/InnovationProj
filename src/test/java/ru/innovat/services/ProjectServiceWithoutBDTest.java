package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.innovat.dao.major.ProjectDao;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.ProjectService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;


public class ProjectServiceWithoutBDTest extends ConfigServiceTest {
    @MockBean
    ProjectDao projectDao;

    @Test
    public void testFindById() {
        Project mockProject = new Project();
        mockProject.setId_project(14);
        mockProject.setNameProject("test");
        mockProject.setExpertiseProject("test");
        mockProject.setSiteProject("test");
        mockProject.setProjectDescription("test");
        mockProject.setNumberPhoneProject("99999999999");
        mockProject.setCurrentStage("test");
        mockProject.setStartDate("2221-04-23");
        mockProject.setLevelSolution("test");
        mockProject.setSolutionProblems("test");
        mockProject.setEssenceInnovations("test");
        mockProject.setCompetitiveAdvantages("test");
        given(this.projectDao.findById(anyInt()))
                .willReturn(mockProject);
        Project project = projectService.findProject(14);
        assertThat(project.getId_project()).isEqualTo(14);
        assertThat(project.getNameProject()).isEqualTo("test");
        assertThat(project.getExpertiseProject()).isEqualTo("test");
        assertThat(project.getSiteProject()).isEqualTo("test");
        assertThat(project.getProjectDescription()).isEqualTo("test");
        assertThat(project.getNumberPhoneProject()).isEqualTo("99999999999");
        assertThat(project.getCurrentStage()).isEqualTo("test");
        assertThat(project.getStartDate()).isEqualTo("2221-04-23");
        assertThat(project.getLevelSolution()).isEqualTo("test");
        assertThat(project.getSolutionProblems()).isEqualTo("test");
        assertThat(project.getEssenceInnovations()).isEqualTo("test");
        assertThat(project.getCompetitiveAdvantages()).isEqualTo("test");
    }

}
