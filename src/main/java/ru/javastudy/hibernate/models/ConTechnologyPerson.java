package ru.javastudy.hibernate.models;
import javax.persistence.*;

@Entity
@Table(name = "connection_category_technology_person", schema = "", catalog = "x92176f5_inovat")
public class ConTechnologyPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_connection_category_technology_person;
    @Column(name = "id_person")
    private int id_person;
    @Column(name = "id_first_level_technology")
    private int id_first_level_technology;
    @Column(name = "id_second_level_technology")
    private int id_second_level_technology;
    @Column(name = "id_third_level_technology")
    private int id_third_level_technology;
    @Column(name = "Rating_category_technology")
    private int Rating_category_technology;

    public int getId_connection_category_technology_person() {
        return id_connection_category_technology_person;
    }

    public void setId_connection_category_technology_person(int id_connection_category_technology_person) {
        this.id_connection_category_technology_person = id_connection_category_technology_person;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
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

    public int getRating_category_technology() {
        return Rating_category_technology;
    }

    public void setRating_category_technology(int rating_category_technology) {
        Rating_category_technology = rating_category_technology;
    }
}
