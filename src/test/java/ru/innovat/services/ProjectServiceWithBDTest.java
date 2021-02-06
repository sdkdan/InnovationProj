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


public class ProjectServiceWithBDTest extends ConfigServiceTest{

    @Test
    public void testFindByIdWithBD() {
        Project project = projectService.findProject(1);
        assertThat(project.getId_project()).isEqualTo(1);
        assertThat(project.getNameProject()).isEqualTo("База данных инновационных проектов");
        assertThat(project.getExpertiseProject()).isEqualTo("Экспертиза проекта:...");
        assertThat(project.getSiteProject()).isEqualTo("dl.spbstu.ru");
        assertThat(project.getProjectDescription()).isEqualTo("Описание проекта:...");
        assertThat(project.getNumberPhoneProject()).isEqualTo("+79213004090");
        assertThat(project.getCurrentStage()).isEqualTo("Текущая стадия:...");
        assertThat(project.getStartDate()).isEqualTo("2020-10-20");
        assertThat(project.getLevelSolution()).isEqualTo("Уровень решения:...");
        assertThat(project.getSolutionProblems()).isEqualTo("Решение проблемы:...");
        assertThat(project.getEssenceInnovations()).isEqualTo("Суть инновации:...");
        assertThat(project.getCompetitiveAdvantages()).isEqualTo("Конкурентные преимущества:...");
    }


    @Test
    public void projectListTest() {
        List<Project> projectList = projectService.projectList();
        assertThat(projectList.get(0).getId_project()).isEqualTo(1);
        assertThat(projectList.get(0).getNameProject()).isEqualTo("База данных инновационных проектов");
        assertThat(projectList.get(0).getExpertiseProject()).isEqualTo("Экспертиза проекта:...");
        assertThat(projectList.get(0).getSiteProject()).isEqualTo("dl.spbstu.ru");
        assertThat(projectList.get(0).getProjectDescription()).isEqualTo("Описание проекта:...");
        assertThat(projectList.get(0).getNumberPhoneProject()).isEqualTo("+79213004090");
        assertThat(projectList.get(0).getCurrentStage()).isEqualTo("Текущая стадия:...");
        assertThat(projectList.get(0).getStartDate()).isEqualTo("2020-10-20");
        assertThat(projectList.get(0).getLevelSolution()).isEqualTo("Уровень решения:...");
        assertThat(projectList.get(0).getSolutionProblems()).isEqualTo("Решение проблемы:...");
        assertThat(projectList.get(0).getEssenceInnovations()).isEqualTo("Суть инновации:...");
        assertThat(projectList.get(0).getCompetitiveAdvantages()).isEqualTo("Конкурентные преимущества:...");
        assertThat(projectList.get(1).getId_project()).isEqualTo(2);
        assertThat(projectList.get(1).getNameProject()).isEqualTo("Просмотр Gachimuchi");
        assertThat(projectList.get(1).getExpertiseProject()).isEqualTo("Экспертиза проекта");
        assertThat(projectList.get(1).getSiteProject()).isEqualTo("Gachi.com");
        assertThat(projectList.get(1).getProjectDescription()).isEqualTo("Описание проекта");
        assertThat(projectList.get(1).getNumberPhoneProject()).isEqualTo("88005553535");
        assertThat(projectList.get(1).getCurrentStage()).isEqualTo("Текущая стадия");
        assertThat(projectList.get(1).getStartDate()).isEqualTo("2020-11-22");
        assertThat(projectList.get(1).getLevelSolution()).isEqualTo("Уровень решения");
        assertThat(projectList.get(1).getSolutionProblems()).isEqualTo("Решение проблемы");
        assertThat(projectList.get(1).getEssenceInnovations()).isEqualTo("Суть инновации");
        assertThat(projectList.get(1).getCompetitiveAdvantages()).isEqualTo("Конкурентные преимущества");
    }


    @Test
    public void updateProjectTest() {
        Project project = projectService.findProject(1);
        project.setCompetitiveAdvantages("test");
        project.setNameProject("test");
        projectService.updateProject(project);
        Project updatedProject = projectService.findProject(1);
        assertThat(project.getId_project()).isEqualTo(updatedProject.getId_project());
        assertThat(project.getNameProject()).isEqualTo(updatedProject.getNameProject());
        assertThat(project.getExpertiseProject()).isEqualTo(updatedProject.getExpertiseProject());
        assertThat(project.getSiteProject()).isEqualTo(updatedProject.getSiteProject());
        assertThat(project.getProjectDescription()).isEqualTo(updatedProject.getProjectDescription());
        assertThat(project.getNumberPhoneProject()).isEqualTo(updatedProject.getNumberPhoneProject());
        assertThat(project.getCurrentStage()).isEqualTo(updatedProject.getCurrentStage());
        assertThat(project.getStartDate()).isEqualTo(updatedProject.getStartDate());
        assertThat(project.getLevelSolution()).isEqualTo(updatedProject.getLevelSolution());
        assertThat(project.getSolutionProblems()).isEqualTo(updatedProject.getSolutionProblems());
        assertThat(project.getEssenceInnovations()).isEqualTo(updatedProject.getEssenceInnovations());
        assertThat(project.getCompetitiveAdvantages()).isEqualTo(updatedProject.getCompetitiveAdvantages());
    }

}
