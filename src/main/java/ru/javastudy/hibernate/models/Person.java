package ru.javastudy.hibernate.models;

import javax.persistence.*;
import java.util.HashSet;
<<<<<<< HEAD

=======
>>>>>>> sdk1
import java.util.Set;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_person;
    @Column(name = "Surname")
    private String surname;
    @Column(name = "Name")
    private String name;
    @Column(name = "Third_name")
    private String third_Name;
    @Column(name = "Phone_number_person")
    private String phone_number_person;
    @Column(name = "Date_of_birth")
    private String date_of_birth;
    @Column(name = "E_mail")
    private String e_mail;
    @Column(name = "Facebook")
    private String facebook;
    @Column(name = "VK")
    private String vk;
    @Column(name = "Rating")
    private int rating;
    @Column(name = "Twitter")
    private String twitter;
    @Column(name = "Comment")
    private String comment;


<<<<<<< HEAD

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "person_event",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_event")
    )private Set<Event> events = new HashSet<Event>();

    public Set<Event> getEvent() {
        return this.events;
    }


=======
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "person_event",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "id_person"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "id_event"))
    public Set<Event> events = new HashSet<>();

    public Set<Event> getEvents() {
        return events;
    }

>>>>>>> sdk1
    public void setEvents(Set<Event> events) {
        this.events = events;
    }

<<<<<<< HEAD
    public void addEvents(Event event){
        events.add(event);
    }


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "organization_person",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_organization")
    )private Set<Organization> organizations = new HashSet<Organization>();

    public Set<Organization> getOrganizations() {
        return this.organizations;
    }


    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public void addEvents(Organization organization){
        organizations.add(organization);
    }


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(name = "project_person",
            joinColumns = @JoinColumn(name = "id_person"),
            inverseJoinColumns = @JoinColumn(name = "id_project")
    )private Set<Project> projects = new HashSet<Project>();

    public Set<Project> getProjects() {
        return this.projects;
    }


    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public void addEvents(Project project){
        projects.add(project);
    }
=======
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "organization_event",
            //foreign key for EmployeeEntity in employee_car table
            joinColumns = @JoinColumn(name = "id_person"),
            //foreign key for other side - EmployeeEntity in employee_car table
            inverseJoinColumns = @JoinColumn(name = "id_organization"))
    public Set<Organization> organizations = new HashSet<>();
>>>>>>> sdk1

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
   
    public String getThirdName() {
        return third_Name;
    }

    public void setThirdName(String third_Name) {
        this.third_Name = third_Name;
    }

    public String getPhone_number_person(){
        return phone_number_person;
    }


    public void setPhone_number_person(String phone_number_person){
        this.phone_number_person = phone_number_person;
    }


    public String getDate_of_birth(){
        return date_of_birth;
    }


    public void setDate_of_birth(String date_of_birth){
        this.date_of_birth = date_of_birth;
    }

    public int getRating(){
        return rating;
    }


    public void setRating(int raiting){
        this.rating = raiting;
    }

    public String getE_mail(){
        return e_mail;
    }

    public void setE_mail(String e_mail){
        this.e_mail = e_mail;
    }


    public String getFacebook(){
        return facebook;
    }

    public void setFacebook(String facebook){
        this.facebook = facebook;
    }

    public String getVk(){
        return vk;
    }

    public void setVk(String vk){
        this.vk = vk;
    }


    public String getTwitter(){
        return twitter;
    }


    public void setTwitter(String twitter){
        this.twitter = twitter;
    }

    public String getComment(){
        return comment;
    }

    public void setComment(String comment){
        this.comment = comment;
    }


}