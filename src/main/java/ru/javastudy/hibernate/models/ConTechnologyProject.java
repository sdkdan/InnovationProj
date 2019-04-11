package ru.javastudy.hibernate.models;
import javax.persistence.*;

@Entity
@Table(name = "connection_category_technology_project", schema = "", catalog = "x92176f5_inovat")
public class ConTechnologyProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_connection_category_technology_project;
    @Column(name = "id_project")
    private int id_project;
    @Column(name = "id_first_level_technology")
    private int id_first_level_technology;
    @Column(name = "id_second_level_technology")
    private int id_second_level_technology;
    @Column(name = "id_third_level_technology")
    private int id_third_level_technology;

    public int getId_connection_category_technology_project() {
        return id_connection_category_technology_project;
    }

    public void setId_connection_category_technology_project(int id_connection_category_technology_project) {
        this.id_connection_category_technology_project = id_connection_category_technology_project;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public int getId_first_level_technology() {
        return id_first_level_technology;
    }

    public void setId_first_level_technology(int id_first_level_technology) {
        this.id_first_level_technology = id_first_level_technology;
    }

    public int getId_second_level_technology() {
        return id_second_level_technology;
    }

    public void setId_second_level_technology(int id_second_level_technology) {
        this.id_second_level_technology = id_second_level_technology;
    }

    public int getId_third_level_technology() {
        return id_third_level_technology;
    }

    public void setId_third_level_technology(int id_third_level_technology) {
        this.id_third_level_technology = id_third_level_technology;
    }
}
