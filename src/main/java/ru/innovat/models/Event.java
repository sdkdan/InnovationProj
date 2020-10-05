package ru.innovat.models;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import ru.innovat.models.utils.TypeEvent;
@Entity
@Indexed
@Table(name = "event", schema = "", catalog = "x92176f5_inovat")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_event;
    @Column(name = "Site_event")
    private String site_event;
    @Field
    @Column(name = "Name_event")
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

    public int getIdTypeEvent() {
        return idTypeEvent;
    }

    public void setIdTypeEvent(int idTypeEvent) {
        this.idTypeEvent = idTypeEvent;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "person_event",
            joinColumns = @JoinColumn(name = "id_event"),
            inverseJoinColumns = @JoinColumn(name = "id_person")
    )
    private Set<Person> persons = new HashSet<Person>();


    public Set<Person> getPersons() {
        return this.persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    public void addPersons(Person person) {
        persons.add(person);
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_event",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "id_event"),
            //foreign key for other side - EmployeeEntity in employee_car table
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
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "id_event"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "id_project"))
    public Set<Project> projects = new HashSet<>();

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="id_type_event", insertable=false, updatable=false)
    private TypeEvent typeEvent;

    public TypeEvent getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(TypeEvent typeEvent) {
        this.typeEvent = typeEvent;
    }



    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getSite_event() {
        return site_event;
    }

    public void setSite_event(String site_event) {
        this.site_event = site_event;
    }

    public String getName_event() {
        return name_event;
    }

    public void setName_event(String name_event) {
        this.name_event = name_event;
    }

    public String getImportance_event() {
        return importance_event;
    }

    public void setImportance_event(String importance_event) {
        this.importance_event = importance_event;
    }

    public String getScope_event() {
        return scope_event;
    }

    public void setScope_event(String scope_event) {
        this.scope_event = scope_event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getDate_event() {
        return date_event;
    }

    public void setDate_event(String date_event) {
        this.date_event = date_event;
    }

    public Date getDate_for_month() {
        return date_for_month;
    }

    public void setDate_for_month(Date date_for_month) {
        this.date_for_month = date_for_month;
    }

    public Date getDate_for_the_week() {
        return date_for_the_week;
    }

    public void setDate_for_the_week(Date date_for_the_week) {
        this.date_for_the_week = date_for_the_week;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getPrizes() {
        return prizes;
    }

    public void setPrizes(String prizes) {
        this.prizes = prizes;
    }

    public String getLocation_event() {
        return location_event;
    }

    public void setLocation_event(String location_event) {
        this.location_event = location_event;
    }
}