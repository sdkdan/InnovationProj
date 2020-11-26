package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.major.Project;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/create-project-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource("/application-test.properties")
@Transactional
public class ProjectServiceWithBDTest {

    @Autowired
    ProjectService projectService;
    @Autowired
    EventService eventService;
    @Autowired
    PersonService personService;
    @Autowired
    OrganizationService organizationService;


    @Test
    public void testFindByIdWithBD() {
        Project project = projectService.findProject(1);
        assertThat(project.getId_project()).isEqualTo(1);
        assertThat(project.getName_project()).isEqualTo("База данных инновационных проектов");
        assertThat(project.getExpertise_project()).isEqualTo("Экспертиза проекта:...");
        assertThat(project.getSite_project()).isEqualTo("dl.spbstu.ru");
        assertThat(project.getProject_description()).isEqualTo("Описание проекта:...");
        assertThat(project.getNumber_Phone_project()).isEqualTo("+79213004090");
        assertThat(project.getCurrent_stage()).isEqualTo("Текущая стадия:...");
        assertThat(project.getStart_date()).isEqualTo("2020-10-20");
        assertThat(project.getLevel_solution()).isEqualTo("Уровень решения:...");
        assertThat(project.getSolution_problems()).isEqualTo("Решение проблемы:...");
        assertThat(project.getEssence_innovations()).isEqualTo("Суть инновации:...");
        assertThat(project.getCompetitive_advantages()).isEqualTo("Конкурентные преимущества:...");
    }


    @Test
    public void projectListTest(){
        List<Project> projectList = projectService.projectList();
        assertThat(projectList.get(0).getId_project()).isEqualTo(1);
        assertThat(projectList.get(0).getName_project()).isEqualTo("База данных инновационных проектов");
        assertThat(projectList.get(0).getExpertise_project()).isEqualTo("Экспертиза проекта:...");
        assertThat(projectList.get(0).getSite_project()).isEqualTo("dl.spbstu.ru");
        assertThat(projectList.get(0).getProject_description()).isEqualTo("Описание проекта:...");
        assertThat(projectList.get(0).getNumber_Phone_project()).isEqualTo("+79213004090");
        assertThat(projectList.get(0).getCurrent_stage()).isEqualTo("Текущая стадия:...");
        assertThat(projectList.get(0).getStart_date()).isEqualTo("2020-10-20");
        assertThat(projectList.get(0).getLevel_solution()).isEqualTo("Уровень решения:...");
        assertThat(projectList.get(0).getSolution_problems()).isEqualTo("Решение проблемы:...");
        assertThat(projectList.get(0).getEssence_innovations()).isEqualTo("Суть инновации:...");
        assertThat(projectList.get(0).getCompetitive_advantages()).isEqualTo("Конкурентные преимущества:...");
        assertThat(projectList.get(1).getId_project()).isEqualTo(2);
        assertThat(projectList.get(1).getName_project()).isEqualTo("Просмотр Gachimuchi");
        assertThat(projectList.get(1).getExpertise_project()).isEqualTo("Экспертиза проекта");
        assertThat(projectList.get(1).getSite_project()).isEqualTo("Gachi.com");
        assertThat(projectList.get(1).getProject_description()).isEqualTo("Описание проекта");
        assertThat(projectList.get(1).getNumber_Phone_project()).isEqualTo("88005553535");
        assertThat(projectList.get(1).getCurrent_stage()).isEqualTo("Текущая стадия");
        assertThat(projectList.get(1).getStart_date()).isEqualTo("2020-11-22");
        assertThat(projectList.get(1).getLevel_solution()).isEqualTo("Уровень решения");
        assertThat(projectList.get(1).getSolution_problems()).isEqualTo("Решение проблемы");
        assertThat(projectList.get(1).getEssence_innovations()).isEqualTo("Суть инновации");
        assertThat(projectList.get(1).getCompetitive_advantages()).isEqualTo("Конкурентные преимущества");
    }


    @Test
    public void updateProjectTest(){
        Project project = projectService.findProject(1);
        project.setCompetitive_advantages("test");
        project.setName_project("test");
        projectService.updateProject(project);
        Project updatedProject = projectService.findProject(1);
        assertThat(project.getId_project()).isEqualTo(updatedProject.getId_project());
        assertThat(project.getName_project()).isEqualTo(updatedProject.getName_project());
        assertThat(project.getExpertise_project()).isEqualTo(updatedProject.getExpertise_project());
        assertThat(project.getSite_project()).isEqualTo(updatedProject.getSite_project());
        assertThat(project.getProject_description()).isEqualTo(updatedProject.getProject_description());
        assertThat(project.getNumber_Phone_project()).isEqualTo(updatedProject.getNumber_Phone_project());
        assertThat(project.getCurrent_stage()).isEqualTo(updatedProject.getCurrent_stage());
        assertThat(project.getStart_date()).isEqualTo(updatedProject.getStart_date());
        assertThat(project.getLevel_solution()).isEqualTo(updatedProject.getLevel_solution());
        assertThat(project.getSolution_problems()).isEqualTo(updatedProject.getSolution_problems());
        assertThat(project.getEssence_innovations()).isEqualTo(updatedProject.getEssence_innovations());
        assertThat(project.getCompetitive_advantages()).isEqualTo(updatedProject.getCompetitive_advantages());
    }

//    @Test
//    public void  addProjectTest(){
//        Project project = new Project();
//        project.setName_project("test");
//        project.setExpertise_project("test");
//        project.setSite_project("test");
//        project.setProject_description("test");
//        project.setNumber_Phone_project("99999999999");
//        project.setCurrent_stage("test");
//        project.setStart_date("2221-04-23");
//        project.setLevel_solution("test");
//        project.setSolution_problems("test");
//        project.setEssence_inovations("test");
//        project.setCompetitive_advantages("test");
//        projectService.addProject(project);
//        verify(projectService,times(1)).addProject(project);
//        Project addedProject = projectService.findProject(15);
//        assertThat(project.getId_project()).isEqualTo(addedProject.getId_project());
//        assertThat(project.getName_project()).isEqualTo(addedProject.getName_project());
//        assertThat(project.getExpertise_project()).isEqualTo(addedProject.getExpertise_project());
//        assertThat(project.getSite_project()).isEqualTo(addedProject.getSite_project());
//        assertThat(project.getProject_description()).isEqualTo(addedProject.getProject_description());
//        assertThat(project.getNumber_Phone_project()).isEqualTo(addedProject.getNumber_Phone_project());
//        assertThat(project.getCurrent_stage()).isEqualTo(addedProject.getCurrent_stage());
//        assertThat(project.getStart_date()).isEqualTo(addedProject.getStart_date());
//        assertThat(project.getLevel_solution()).isEqualTo(addedProject.getLevel_solution());
//        assertThat(project.getSolution_problems()).isEqualTo(addedProject.getSolution_problems());
//        assertThat(project.getEssence_inovations()).isEqualTo(addedProject.getEssence_inovations());
//        assertThat(project.getCompetitive_advantages()).isEqualTo(addedProject.getCompetitive_advantages());
//    }


}
