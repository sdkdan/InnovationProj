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
@Table(name = "project")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_project;
    @Field
    @Column(name = "Name_project")
    @EqualsAndHashCode.Include
    private String name_project;
    @Column(name = "Essence_innovation")
    private String essence_inovations;
    @Column(name = "Solution_problem")
    private String solution_problems;
    @Column(name = "Level_solution")
    private String level_solution;
    @Column(name = "Competitive_advantages")
    private String competitive_advantages;
    @Column(name = "Start_date")
    private String start_date;
    @Column(name = "Current_stage")
    private String current_stage;
    @Column(name = "Expertise_project")
    private String expertise_project;
    @Column(name = "Project_description")
    private String project_description;
    @Column(name = "Site_project")
    private String site_project;
    @Column(name = "Number_Phone_project")
    private String number_Phone_project;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_person",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_person")
    )
    private Set<Person> persons = new HashSet<Person>();

    public Set<Person> getPersons() {
        return this.persons;
    }


    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPerson(Person person) {
        persons.add(person);
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_project",

            joinColumns = @JoinColumn(name = "id_project"),

            inverseJoinColumns = @JoinColumn(name = "id_organization"))
    public Set<Organization> organizations = new HashSet<>();

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "event_project",

            joinColumns = @JoinColumn(name = "id_project"),

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

}