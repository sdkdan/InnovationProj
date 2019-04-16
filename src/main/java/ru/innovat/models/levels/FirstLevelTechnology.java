package ru.innovat.models.levels;

import javax.persistence.*;

@Entity
@Table(name = "first_level_technology")
public class FirstLevelTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_first_level_technology;
    @Column(name = "Name_first_level_technology")
    private String name_first_level_technology;

    public int getId_first_level_technology() {
        return id_first_level_technology;
    }

    public void setId_first_level_technology(int id_first_level_technology) {
        this.id_first_level_technology = id_first_level_technology;
    }

    public String getName_first_level_technology() {
        return name_first_level_technology;
    }

    public void setName_first_level_technology(String name_first_level_technology) {
        this.name_first_level_technology = name_first_level_technology;
    }
}







