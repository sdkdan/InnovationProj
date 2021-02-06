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
        mockProject.setName_project("test");
        mockProject.setExpertise_project("test");
        mockProject.setSite_project("test");
        mockProject.setProject_description("test");
        mockProject.setNumber_Phone_project("99999999999");
        mockProject.setCurrent_stage("test");
        mockProject.setStart_date("2221-04-23");
        mockProject.setLevel_solution("test");
        mockProject.setSolution_problems("test");
        mockProject.setEssence_innovations("test");
        mockProject.setCompetitive_advantages("test");
        given(this.projectDao.findById(anyInt()))
                .willReturn(mockProject);
        Project project = projectService.findProject(14);
        assertThat(project.getId_project()).isEqualTo(14);
        assertThat(project.getName_project()).isEqualTo("test");
        assertThat(project.getExpertise_project()).isEqualTo("test");
        assertThat(project.getSite_project()).isEqualTo("test");
        assertThat(project.getProject_description()).isEqualTo("test");
        assertThat(project.getNumber_Phone_project()).isEqualTo("99999999999");
        assertThat(project.getCurrent_stage()).isEqualTo("test");
        assertThat(project.getStart_date()).isEqualTo("2221-04-23");
        assertThat(project.getLevel_solution()).isEqualTo("test");
        assertThat(project.getSolution_problems()).isEqualTo("test");
        assertThat(project.getEssence_innovations()).isEqualTo("test");
        assertThat(project.getCompetitive_advantages()).isEqualTo("test");
    }

}
