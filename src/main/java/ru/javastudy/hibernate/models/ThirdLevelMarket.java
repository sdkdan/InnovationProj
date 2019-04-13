package ru.javastudy.hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "third_level_market")
public class ThirdLevelMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_third_level_market;
    @Column(name = "id_second_level_market")
    private int id_second_level_market;
    @Column(name = "Name_third_level_market")
    private String name_third_level_market;

    public int getId_third_level_market() {
        return id_third_level_market;
    }

    public void setId_third_level_market(int id_third_level_market) {
        this.id_third_level_market = id_third_level_market;
    }

    public int getId_second_level_market() {
        return id_second_level_market;
    }

    public void setId_second_level_market(int id_second_level_market) {
        this.id_second_level_market = id_second_level_market;
    }

    public String getName_third_level_market() {
        return name_third_level_market;
    }

    public void setName_third_level_market(String name_third_level_market) {
        this.name_third_level_market = name_third_level_market;
    }
}