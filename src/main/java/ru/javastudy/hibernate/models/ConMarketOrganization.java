package ru.javastudy.hibernate.models;


import javax.persistence.*;

@Entity
@Table(name = "connection_category_market_organization")
public class ConMarketOrganization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_connection_category_market_organization;
    @Column(name = "id_project")
    private int id_project;
    @Column(name = "id_first_level_market")
    private int id_first_level_market;
    @Column(name = "id_second_level_market")
    private int id_second_level_market;
    @Column(name = "id_third_level_market")
    private int id_therd_level_market;


    public int getId_connection_category_market_organization() {
        return id_connection_category_market_organization;
    }

    public void setId_connection_category_market_organization(int id_connection_category_market_organization) {
        this.id_connection_category_market_organization = id_connection_category_market_organization;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
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

    public int getId_therd_level_market() {
        return id_therd_level_market;
    }

    public void setId_therd_level_market(int id_therd_level_market) {
        this.id_therd_level_market = id_therd_level_market;
    }
}

