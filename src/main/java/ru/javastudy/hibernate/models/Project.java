package ru.javastudy.hibernate.models;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_project;
    @Column(name = "Name_project")
    private String name_project;
    @Column(name = "Essence_innovation")
    private String essence_inovations;
    @Column(name = "Solution_problem")
    private String solution_problems;
    @Column(name = "Level_solution")
    private String level_solution;
    @Column(name = "Competitive_advantages")
    private String competitive_advantages;
    @Column(name = "Start_date")
    private Date start_date;
    @Column(name = "Current_stage")
    private String current_stage;
    @Column(name = "Expertise_project")
    private String expertise_project;
    @Column(name = "Project_description")
    private String project_description;
    @Column(name = "Site_project")
    private String site_project;
    @Column(name = "Number_Phone_project")
    private String number_Phone_project;

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public String getName_project() {
        return name_project;
    }

    public void setName_project(String name_project) {
        this.name_project = name_project;
    }

    public String getEssence_inovations() {
        return essence_inovations;
    }

    public void setEssence_inovations(String essence_inovations) {
        this.essence_inovations = essence_inovations;
    }

    public String getSolution_problems() {
        return solution_problems;
    }

    public void setSolution_problems(String solution_problems) {
        this.solution_problems = solution_problems;
    }

    public String getLevel_solution() {
        return level_solution;
    }

    public void setLevel_solution(String level_solution) {
        this.level_solution = level_solution;
    }

    public String getCompetitive_advantages() {
        return competitive_advantages;
    }

    public void setCompetitive_advantages(String competitive_advantages) {
        this.competitive_advantages = competitive_advantages;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public String getCurrent_stage() {
        return current_stage;
    }

    public void setCurrent_stage(String current_stage) {
        this.current_stage = current_stage;
    }

    public String getExpertise_project() {
        return expertise_project;
    }

    public void setExpertise_project(String expertise_project) {
        this.expertise_project = expertise_project;
    }

    public String getProject_description() {
        return project_description;
    }

    public void setProject_description(String project_description) {
        this.project_description = project_description;
    }

    public String getSite_project() {
        return site_project;
    }

    public void setSite_project(String site_project) {
        this.site_project = site_project;
    }

    public String getNumber_Phone_project() {
        return number_Phone_project;
    }

    public void setNumber_Phone_project(String number_Phone_project) {
        this.number_Phone_project = number_Phone_project;
    }
}