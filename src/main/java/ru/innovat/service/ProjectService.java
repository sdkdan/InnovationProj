package ru.innovat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.ProjectDao;
import ru.innovat.models.Project;

import java.util.List;


@Service
public class ProjectService {

    public ProjectService(){

    }

    @Autowired
    private ProjectDao projectDao = new ProjectDao();

    @Transactional
    public void setProjectDao(ProjectDao projectDao){
        this.projectDao = projectDao;
    }

    @Transactional
    public Project findProject(int id) {
        return projectDao.findById(id);
    }

    @Transactional
    public void addProject(Project project) {
        this.projectDao.add(project);
    }

    @Transactional
    public void deleteProject(int id) {
        projectDao.delete(id);
    }

    @Transactional
    public void updateProject(Project project) {
        projectDao.update(project);
    }

    @Transactional
    public List<Project> projectList() {
        return projectDao.projectList();
    }



}
