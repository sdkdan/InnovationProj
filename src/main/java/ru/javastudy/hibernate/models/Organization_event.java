package ru.javastudy.hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "Organization_event")
public class Organization_event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_organization_event;
    @Column(name = "id_organization")
    private int id_organization;
    @Column(name = "id_event")
    private int id_event;


    public int getId_organization_event() {
        return id_organization_event;
    }

    public void setId_organization_event(int id_organization_event) {
        this.id_organization_event = id_organization_event;
    }

    public int getId_organization() {
        return id_organization;
    }

    public void setId_organization(int id_organization) {
        this.id_organization = id_organization;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
}
