package ru.innovat.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.ProjectDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.search.ProjectSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
