package ru.javastudy.hibernate.models;

import javax.persistence.*;
//категории рынков
@Entity
@Table(name = "first_level_market")
public class FirstLevelMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_first_level_market;
    @Column(name = "Name_first_level_market")
    private String name_first_level_market;

    public int getId_first_level_market() {
        return id_first_level_market;
    }

    public void setId_first_level_market(int id_first_level_market) {
        this.id_first_level_market = id_first_level_market;
    }

    public String getName_first_level_market() {
        return name_first_level_market;
    }

    public void setName_first_level_market(String name_first_level_market) {
        this.name_first_level_market = name_first_level_market;
    }
}


