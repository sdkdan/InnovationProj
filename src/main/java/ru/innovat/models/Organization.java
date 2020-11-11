package ru.innovat.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Indexed
@Table(name = "organization")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_organization;
    @Field
    @Column(name = "Name_organization")
    @EqualsAndHashCode.Include
    private String name_organization;
    @Column(name = "Site_organization")
    private String site_organization;
    @Column(name = "City_organization")
    @Field
    private String city_organization;
    @Column(name = "Notes_organization")
    private String notes_organization;


    @ManyToMany(fetch = FetchType.LAZY)
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

    public void addEvent(Event event) {
        events.add(event);
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public void addPerson(Person person) {
        persons.add(person);
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_project",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )
    private Set<Project> projects = new HashSet<Project>();

    public Set<Project> getProjects() {
        return this.projects;
    }


    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

}
