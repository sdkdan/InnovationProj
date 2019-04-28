package ru.innovat.models.levels;

import javax.persistence.*;

@Entity
@Table(name = "third_level_technology")
public class ThirdLevelTechnology {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_third_level_technology;
    @Column(name = "id_second_level_technology")
    private int id_second_level_technology;
    @Column(name = "Name_third_level_technology")
    private String name_third_level_technology;

    public int getId_third_level_technology() {
        return id_third_level_technology;
    }

    public void setId_third_level_technology(int id_third_level_technology) {
        this.id_third_level_technology = id_third_level_technology;
    }

    public int getId_second_level_technology() {
        return id_second_level_technology;
    }

    public void setId_second_level_technology(int id_second_level_technology) {
        this.id_second_level_technology = id_second_level_technology;
    }

    public String getName_third_level_technology() {
        return name_third_level_technology;
    }

    public void setName_third_level_technology(String name_third_level_technology) {
        this.name_third_level_technology = name_third_level_technology;
    }
}




