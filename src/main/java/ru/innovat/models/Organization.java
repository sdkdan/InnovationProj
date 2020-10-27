package ru.innovat.models;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Table(name = "organization")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_organization;
    @Field
    @Column(name = "Name_organization")
    private String name_organization;
    @Column(name = "Site_organization")
    private String site_organization;
    @Column(name = "City_organization")
    @Field
    private String city_organization;
    @Column(name = "Notes_organization")
    private String notes_organization;

    @ManyToMany(fetch = FetchType.LAZY )
    @JoinTable(name = "organization_event",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    public Set<Event> events = new HashSet<>();

    public Set<Event> getEvents() {
        return events;
    }

    public void setEvents(Set<Event> events) {
        this.events = events;
    }

    public void addEvent(Event event){
        events.add(event);
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "organization_person",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_person"))
    public Set<Person> persons = new HashSet<>();

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person){
        persons.add(person);
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "organization_project",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )private Set<Project> projects = new HashSet<Project>();

    public Set<Project> getProjects() {
        return this.projects;
    }


    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project){
        projects.add(project);
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
