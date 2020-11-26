package ru.innovat.service.major;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.major.ProjectDao;
import ru.innovat.models.major.Project;

import java.util.ArrayList;


@Service
@AllArgsConstructor
public class ProjectService {
    private final ProjectDao projectDao;

    @Transactional
    public Project findProject(int id) {
        return projectDao.findById(id);
    }

    @Transactional
    public void addProject(Project project) { this.projectDao.add(project); }

    @Transactional
    public void deleteProject(int id) {
        projectDao.delete(id);
    }

    @Transactional
    public void updateProject(Project project) {
        projectDao.update(project);
    }

    @Transactional
    public ArrayList<Project> projectList() {
        return projectDao.projectList();
    }

    @Transactional
    public Project projectAllConnections(int id) {
        return projectDao.projectAllConnections(id);
    }
}
