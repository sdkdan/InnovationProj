package ru.innovat.service.major;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.major.ProjectDao;
import ru.innovat.models.major.Project;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectDao projectDao;

    @Transactional
    public Project findProject(int id) {
        return projectDao.findById(id);
    }

    @Transactional
    public void addProject(Project project) { projectDao.add(project); }

    @Transactional
    public void deleteProject(int id) {
        projectDao.delete(id);
    }

    @Transactional
    public void updateProject(Project project) {
        projectDao.update(project);
    }

    public ArrayList<Project> projectList() {
        return projectDao.projectList();
    }

    @Transactional
    public Project projectAllConnections(int id) {
        return projectDao.projectAllConnections(id);
    }
}
