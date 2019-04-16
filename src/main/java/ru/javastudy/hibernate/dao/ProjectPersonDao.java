//package ru.javastudy.hibernate.dao;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import ru.javastudy.hibernate.models.ProjectPerson;
//
//import java.util.List;
//
//@Repository
//public class ProjectPersonDao {
//
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    public void setSessionFactory(SessionFactory sessionFactory){
//        this.sessionFactory = sessionFactory;
//    }
//
//    public ProjectPerson findById(int id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        ProjectPerson projectPerson = (ProjectPerson) session.load(ProjectPerson.class, id);
//
//        return projectPerson;
//    }
//
//
//    public void add(ProjectPerson projectPerson) {
//        Session session = this.sessionFactory.getCurrentSession();
//        session.persist(projectPerson);
//    }
//
//
//
//    public void delete(int id) {
//        Session session = this.sessionFactory.getCurrentSession();
//        ProjectPerson projectPerson = (ProjectPerson)session.load(ProjectPerson.class, id);
//
//        if(projectPerson != null){
//            session.delete(projectPerson);
//        }
//    }
//
//
//    @SuppressWarnings("unchecked")
//    public List<ProjectPerson> projectPersonList() {
//        Session session = this.sessionFactory.getCurrentSession();
//        List<ProjectPerson> projectPersonList = (List<ProjectPerson>) session.createQuery("From ProjectPerson").list();
//        return projectPersonList;
//
//    }
//
//}
//
//
