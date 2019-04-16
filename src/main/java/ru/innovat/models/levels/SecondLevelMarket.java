package ru.innovat.models.levels;


import javax.persistence.*;

@Entity
@Table(name = "second_level_market")
public class SecondLevelMarket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_second_level_market;
    @Column(name = "id_first_level_market")
    private int id_first_level_market;
    @Column(name = "Name_second_level_market")
    private String name_second_level_market;

    public int getId_second_level_market() {
        return id_second_level_market;
    }

    public void setId_second_level_market(int id_second_level_market) {
        this.id_second_level_market = id_second_level_market;
    }

    public int getId_first_level_market() {
        return id_first_level_market;
    }

    public void setId_first_level_market(int id_first_level_market) {
        this.id_first_level_market = id_first_level_market;
    }

    public String getName_second_level_market() {
        return name_second_level_market;
    }

    public void setName_second_level_market(String name_second_level_market) {
        this.name_second_level_market = name_second_level_market;
    }
}
