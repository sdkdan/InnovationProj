package ru.javastudy.hibernate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.EventDao;
import ru.javastudy.hibernate.dao.EventProjectDao;
import ru.javastudy.hibernate.dao.ProjectDao;
import ru.javastudy.hibernate.models.Event;
import ru.javastudy.hibernate.models.EventProject;
import ru.javastudy.hibernate.models.Project;

import java.util.ArrayList;
import java.util.List;


public class EventProjectService {
    public EventProjectService(){

    }

    @Autowired
    private EventProjectDao eventProjectDao = new EventProjectDao();

    @Transactional
    public void setEventProjectDao(EventProjectDao EventProjectDao){
        this.eventProjectDao = EventProjectDao;
    }


    @Transactional
    public void addEventProject(EventProject EventProject) {
        eventProjectDao.add(EventProject);
    }

    @Transactional
    public void deleteEventProject(int id) {
        eventProjectDao.delete(id);
    }



    @Transactional
    public List<Event> EventList(int id) {
        List<EventProject> eventProjectList = eventProjectDao.eventList(id);
        ArrayList<Event> eventList = new ArrayList<Event>();
        EventDao eventDao = new EventDao();
        Event event;
        for (EventProject eventProject : eventProjectList) {
           event = eventDao.findById(eventProject.getId_event());
           eventList.add(event);
        }
        return eventList;
    }


    @Transactional
    public List<Project> ProjectList(int id) {
        List<EventProject> eventProjectList = eventProjectDao.projectList(id);
        ArrayList<Project> projectList = new ArrayList<Project>();
        ProjectDao projectDao = new ProjectDao();
        Project project;
        for (EventProject eventProject : eventProjectList) {
            project = projectDao.findById(eventProject.getId_project());
            projectList.add(project);
        }
        return projectList;
    }
}
