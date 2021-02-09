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
@Table(name = "organization")
@Getter
@Setter
@Indexed
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_organization;

    @Field
    @Column(name = "Name_organization")
    @EqualsAndHashCode.Include
    private String nameOrganization;

    @Column(name = "Site_organization")
    private String siteOrganization;

    @Column(name = "City_organization")
    @Field
    private String cityOrganization;

    @Column(name = "Notes_organization")
    private String notesOrganization;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "organization_event",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    public Set<Event> events = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_person",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_person"))
    public Set<Person> persons = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_project",
            joinColumns = @JoinColumn(name = "id_organization"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )
    private Set<Project> projects = new HashSet<>();

    public void addPerson(Person person) {
        persons.add(person);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

}
