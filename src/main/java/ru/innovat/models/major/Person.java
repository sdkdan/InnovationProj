package ru.innovat.models.major;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Table(name = "person")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Person {
    static final Logger logger = LoggerFactory.getLogger(Person.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_person;
    @Column(name = "Surname")
    @Field
    private String surname;
    @Column(name = "Name")
    @Field
    @EqualsAndHashCode.Include
    private String name;
    @Column(name = "Third_name")
    private String thirdName;
    @Column(name = "Phone_number")
    private String phoneNumber;
    @Column(name = "Date_of_birth")
    private String dateOfBirth;
    @Column(name = "E_mail")
    private String eMail;
    @Column(name = "Facebook")
    private String facebook;
    @Column(name = "VK")
    private String vk;
    @Column(name = "Rating")
    private String rating;
    @Column(name = "Twitter")
    private String twitter;
    @Column(name = "Comment")
    private String comment;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "person_event",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    public Set<Event> events = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_person",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_organization")
    )
    private Set<Organization> organizations = new HashSet<Organization>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "project_person",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )
    private Set<Project> projects = new HashSet<Project>();

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }

}
