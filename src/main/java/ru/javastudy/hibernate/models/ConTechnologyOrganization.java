package ru.javastudy.hibernate.models;
import javax.persistence.*;

@Entity
@Table(name = "connection_category_technology_organization", schema = "", catalog = "x92176f5_inovat")
public class ConTechnologyOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_connection_category_technology_organization;
    @Column(name = "id_organization")
    private int id_organization;
    @Column(name = "id_first_level_technology")
    private int id_first_level_technology;
    @Column(name = "id_second_level_technology")
    private int id_second_level_technology;
    @Column(name = "id_third_level_technology")
    private int id_third_level_technology;

    public int getId_connection_category_technology_organization() {
        return id_connection_category_technology_organization;
    }

    public void setId_connection_category_technology_organization(int id_connection_category_technology_organization) {
        this.id_connection_category_technology_organization = id_connection_category_technology_organization;
    }

    public int getId_organization() {
        return id_organization;
    }

    public void setId_organization(int id_organization) {
        this.id_organization = id_organization;
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
