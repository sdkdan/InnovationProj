package ru.innovat.models.major;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import ru.innovat.models.utils.TypeEvent;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Indexed
@Table(name = "event")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_event;
    @Column(name = "Site_event")
    private String site_event;
    @Field
    @Column(name = "Name_event")
    @EqualsAndHashCode.Include
    private String name_event;
    @Field
    @Column(name = "Importance_event")
    private String importance_event;
    @Field
    @Column(name = "Scope_event")
    private String scope_event;
    @Column(name = "Description")
    private String description;
    @Column(name = "Phone_number")
    private String phone_number;
    @Column(name = "Date_event")
    private String date_event;
    @Column(name = "Date_for_month")
    private Date date_for_month;
    @Column(name = "Date_for_the_week")
    private Date date_for_the_week;
    @Column(name = "Comment")
    private String comment;
    @Column(name = "Prizes")
    private String prizes;
    @Column(name = "Location_event")
    private String location_event;
    @Column(name = "id_type_event")
    private int idTypeEvent;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_event",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_person")
    )
    private Set<Person> persons = new HashSet<Person>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_event",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_organization"))
    public Set<Organization> organizations = new HashSet<>();
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "event_project",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    public Set<Project> projects = new HashSet<>();
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_type_event", insertable = false, updatable = false)
    private TypeEvent typeEvent;

    public void addPersons(Person person) {
        persons.add(person);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void addOrganization(Organization organization) {
        organizations.add(organization);
    }
}