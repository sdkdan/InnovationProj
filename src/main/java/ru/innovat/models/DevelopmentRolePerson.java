package ru.innovat.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "development_role_person", schema = "", catalog = "x92176f5_inovat")
public class DevelopmentRolePerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_development_role;


    @Basic
    @Column(name = "Name_development_role")
    private String name_development_role;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Person> personList = new ArrayList<>();

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public int getId_development_role() {
        return id_development_role;
    }

    public void setId_development_role(int id_development_role) {
        this.id_development_role = id_development_role;
    }

    public String getName_development_role() {
        return name_development_role;
    }

    public void setName_development_role(String name_development_role) {
        this.name_development_role = name_development_role;
    }
}
