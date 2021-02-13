package ru.innovat.models.major;


import lombok.*;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_project;

    @Field
    @Column(name = "Name_project")
    @EqualsAndHashCode.Include
    private String nameProject;

    @Column(name = "Essence_innovation")
    private String essenceInnovations;

    @Column(name = "Solution_problem")
    private String solutionProblems;

    @Column(name = "Level_solution")
    private String levelSolution;

    @Column(name = "Competitive_advantages")
    private String competitiveAdvantages;

    @Column(name = "Start_date")
    private String startDate;

    @Column(name = "Current_stage")
    private String currentStage;

    @Column(name = "Expertise_project")
    private String expertiseProject;

    @Column(name = "Project_description")
    private String projectDescription;

    @Column(name = "Site_project")
    private String siteProject;

    @Column(name = "Number_Phone_project")
    private String numberPhoneProject;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "project_person",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_person")
    )
    private Set<Person> persons = new HashSet<>();

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