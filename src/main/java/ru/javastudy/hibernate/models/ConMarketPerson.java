package ru.javastudy.hibernate.models;
import javax.persistence.*;

@Entity
@Table(name = "connection_category_market_person", schema = "", catalog = "x92176f5_inovat")
public class ConMarketPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_connetction_category_market_person;
    @Column(name = "id_person")
    private String id_person;
    @Column(name = "id_first_level_market")
    private int id_first_level_market;
    @Column(name = "id_second_level_market")
    private int id_second_level_market;
    @Column(name = "id_third_level_market")
    private int id_third_level_market;
    @Column(name = "Rating_category_market")
    private int Rating_category_market;

    public int getId_connetction_category_market_person() {
        return id_connetction_category_market_person;
    }

    public void setId_connetction_category_market_person(int id_connetction_category_market_person) {
        this.id_connetction_category_market_person = id_connetction_category_market_person;
    }

    public String getId_person() {
        return id_person;
    }

    public void setId_person(String id_person) {
        this.id_person = id_person;
    }

    public int getId_first_level_market() {
        return id_first_level_market;
    }

    public void setId_first_level_market(int id_first_level_market) {
        this.id_first_level_market = id_first_level_market;
    }

    public int getId_second_level_market() {
        return id_second_level_market;
    }

    public void setId_second_level_market(int id_second_level_market) {
        this.id_second_level_market = id_second_level_market;
    }

    public int getId_third_level_market() {
        return id_third_level_market;
    }

    public void setId_third_level_market(int id_third_level_market) {
        this.id_third_level_market = id_third_level_market;
    }

    public int getRating_category_market() {
        return Rating_category_market;
    }

    public void setRating_category_market(int rating_category_market) {
        Rating_category_market = rating_category_market;
    }
}
