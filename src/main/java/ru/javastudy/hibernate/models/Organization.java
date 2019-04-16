package ru.javastudy.hibernate.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_organization;
    @Column(name = "Name_organization")
    private String name_organization;
    @Column(name = "Site_organization")
    private String site_organization;
    @Column(name = "City_organization")
    private String city_organization;
    @Column(name = "Notes_organization")
    private String notes_organization;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "organization_person",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_person")
    )private Set<Person> persons = new HashSet<Person>();

    public Set<Person> getPersons() {
        return this.persons;
    }


    public void setOrganizations(Set<Person> persons) {
        this.persons = persons;
    }

    public void addEvents(Person person){
        persons.add(person);
    }


    public int getId_organization() {
        return id_organization;
    }

    public void setId_organization(int id_organization) {
        this.id_organization = id_organization;
    }

    public String getName_organization() {
        return name_organization;
    }

    public void setName_organization(String name_organization) {
        this.name_organization = name_organization;
    }

    public String getSite_organization() {
        return site_organization;
    }

    public void setSite_organization(String site_organization) {
        this.site_organization = site_organization;
    }

    public String getCity_organization() {
        return city_organization;
    }

    public void setCity_organization(String city_organization) {
        this.city_organization = city_organization;
    }

    public String getNotes_organization() {
        return notes_organization;
    }

    public void setNotes_organization(String notes_organization) {
        this.notes_organization = notes_organization;
    }
}
