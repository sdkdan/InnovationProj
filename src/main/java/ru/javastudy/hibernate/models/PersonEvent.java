package ru.javastudy.hibernate.models;


import javax.persistence.*;

@Entity
@Table(name = "person_event")
public class PersonEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_person_event;
    @Column(name = "id_person")
    private int id_person;
    @Column(name ="id_event")
    private int id_event;


    public int getId_person_event() {
        return id_person_event;
    }

    public void setId_person_event(int id_person_event) {
        this.id_person_event = id_person_event;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }
}
