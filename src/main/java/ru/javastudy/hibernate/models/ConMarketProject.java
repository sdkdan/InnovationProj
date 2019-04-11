package ru.javastudy.hibernate.models;
import javax.persistence.*;

@Entity
@Table(name = "connection_category_market_project", schema = "", catalog = "x92176f5_inovat")
public class ConMarketProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_connetction_category_market_project;
    @Column(name = "id_project")
    private int id_person;
    @Column(name = "id_first_level_market")
    private int id_first_level_market;
    @Column(name = "id_second_level_market")
    private int id_second_level_market;
    @Column(name = "id_third_level_market")
    private int id_third_level_market;

    public int getId_connetction_category_market_project() {
        return id_connetction_category_market_project;
    }

    public void setId_connetction_category_market_project(int id_connetction_category_market_project) {
        this.id_connetction_category_market_project = id_connetction_category_market_project;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
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
}
