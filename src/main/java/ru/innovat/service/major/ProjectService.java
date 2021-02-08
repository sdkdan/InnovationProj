package ru.innovat.service.major;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innovat.dao.major.ProjectDao;
import ru.innovat.models.major.Project;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectDao projectDao;

    public Project findProject(int id) {
        return projectDao.findById(id);
    }

    public void addProject(Project project) { projectDao.add(project); }

    public void deleteProject(int id) {
        projectDao.delete(id);
    }

    public void updateProject(Project project) {
        projectDao.update(project);
    }

    public ArrayList<Project> projectList() {
        return projectDao.projectList();
    }

    public Project projectAllConnections(int id) {
        return projectDao.projectAllConnections(id);
    }
}
