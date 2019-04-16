//package ru.javastudy.hibernate.models;
//import javax.persistence.*;
//
//
//@Entity
//@Table(name = "Project_Person")
//public class ProjectPerson {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id_project_person;
//    @Column(name = "id_project")
//    private int id_project;
//    @Column(name = "id_person")
//    private int id_person;
//    @Column(name = "id_development_role")
//    private int id_development_role;
//    private String nameProject;
//    private String firstNamePerson;
//    private String thirdNamePerson;
//    private String surNamePerson;
//
//    public int getId_project_person() {
//        return id_project_person;
//    }
//
//    public void setId_project_person(int id_project_person) {
//        this.id_project_person = id_project_person;
//    }
//
//    public int getId_project() {
//        return id_project;
//    }
//
//    public void setId_project(int id_project) {
//        this.id_project = id_project;
//    }
//
//    public int getId_person() {
//        return id_person;
//    }
//
//    public void setId_person(int id_person) {
//        this.id_person = id_person;
//    }
//
//    public int getId_development_role() {
//        return id_development_role;
//    }
//
//    public void setId_development_role(int id_development_role) {
//        this.id_development_role = id_development_role;
//    }
//
//    public String getNameProject() {
//        return nameProject;
//    }
//
//    public void setNameProject(String nameProject) {
//        this.nameProject = nameProject;
//    }
//
//    public String getFirstNamePerson() {
//        return firstNamePerson;
//    }
//
//    public void setFirstNamePerson(String firstNamePerson) {
//        this.firstNamePerson = firstNamePerson;
//    }
//
//    public String getThirdNamePerson() {
//        return thirdNamePerson;
//    }
//
//    public void setThirdNamePerson(String thirdNamePerson) {
//        this.thirdNamePerson = thirdNamePerson;
//    }
//
//    public String getSurNamePerson() {
//        return surNamePerson;
//    }
//
//    public void setSurNamePerson(String surNamePerson) {
//        this.surNamePerson = surNamePerson;
//    }
//}
