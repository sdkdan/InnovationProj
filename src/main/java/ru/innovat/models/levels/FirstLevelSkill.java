package ru.innovat.models.levels;

import javax.persistence.*;

@Entity
@Table(name = "first_level_skill")
public class FirstLevelSkill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_first_level_skill;
    @Column(name = "Name_first_level_skill")
    private String name_first_level_skill;

    public int getId_first_level_skill() {
        return id_first_level_skill;
    }

    public void setId_first_level_skill(int id_first_level_skill) {
        this.id_first_level_skill = id_first_level_skill;
    }

    public String getName_first_level_skill() {
        return name_first_level_skill;
    }

    public void setName_first_level_skill(String name_first_level_skill) {
        this.name_first_level_skill = name_first_level_skill;
    }
}




