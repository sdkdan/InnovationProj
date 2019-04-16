package ru.innovat.models.levels;

import javax.persistence.*;

@Entity
@Table(name = "second_level_skill")
public class SecondLevelSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_second_level_skill;
    @Column(name = "id_first_level_skill")
    private int id_first_level_skill;
    @Column(name = "Name_second_level")
    private String name_second_level;

    public int getId_second_level_skill() {
        return id_second_level_skill;
    }

    public void setId_second_level_skill(int id_second_level_skill) {
        this.id_second_level_skill = id_second_level_skill;
    }

    public int getId_first_level_skill() {
        return id_first_level_skill;
    }

    public void setId_first_level_skill(int id_first_level_skill) {
        this.id_first_level_skill = id_first_level_skill;
    }

    public String getName_second_level() {
        return name_second_level;
    }

    public void setName_second_level(String name_second_level) {
        this.name_second_level = name_second_level;
    }
}


