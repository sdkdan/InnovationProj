package ru.innovat.models.major;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.HashSet;
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
    private String essence_innovations;
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
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "organization_project",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_organization"))
    public Set<Organization> organizations = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_project",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    public Set<Event> events = new HashSet<>();

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

    public void addPerson(Person person) { persons.add(person);}
}