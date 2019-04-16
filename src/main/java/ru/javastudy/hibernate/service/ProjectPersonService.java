//package ru.javastudy.hibernate.service;
//
//import org.hibernate.Session;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ru.javastudy.hibernate.dao.ProjectPersonDao;
//import ru.javastudy.hibernate.models.ProjectPerson;
//
//import java.util.List;
//
//
//@Service
//public class ProjectPersonService {
//
//    public ProjectPersonService(){
//
//    }
//
//    @Autowired
//    private ProjectPersonDao projectPersonDao = new ProjectPersonDao();
//
//    @Transactional
//    public void setProjectPersonDao(ProjectPersonDao projectPersonDao){
//        this.projectPersonDao = projectPersonDao;
//    }
//
//    @Transactional
//    public ProjectPerson findProjectPerson(int id) {
//        return projectPersonDao.findById(id);
//    }
//
//    @Transactional
//    public void addProject(ProjectPerson projectPerson) {
//        this.projectPersonDao.add(projectPerson);
//    }
//
//    @Transactional
//    public void deleteProjectPerson(int id) {
//        projectPersonDao.delete(id);
//    }
//
//
//
//    @Transactional
//    public List<ProjectPerson> projectPersonList() {
//
//        return projectPersonDao.projectPersonList();
//    }
//
//
//
//}
//
//
