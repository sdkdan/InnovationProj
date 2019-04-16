package ru.javastudy.hibernate.models;

import javax.persistence.*;

@Entity
@Table(name = "event_project")
public class EventProject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_event_project;
    @Column(name = "id_project")
    private int id_project;
    @Column(name = "id_event")
    private int id_event;


    public int getId_event_project() {
        return id_event_project;
    }

    public void setId_event_project(int id_event_project) {
        this.id_event_project = id_event_project;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
}
