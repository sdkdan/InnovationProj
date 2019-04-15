package ru.javastudy.hibernate.models;


import javax.persistence.*;

@Entity
@Table(name = "organization_person")
public class OrganizationPerson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_organization_person;
    @Column(name = "id_organization")
    private int id_organiztion;
    @Column(name = "id_person")
    private int id_Person;
    private String nameOrganization;
    private String namePerson;
    private String surnamePerson;
    private String thridnamePerson;

    public String getSurnamePerson() {
        return surnamePerson;
    }

    public void setSurnamePerson(String surnamePerson) {
        this.surnamePerson = surnamePerson;
    }

    public String getThridnamePerson() {
        return thridnamePerson;
    }

    public void setThridnamePerson(String thridnamePerson) {
        this.thridnamePerson = thridnamePerson;
    }

    public String getNameOrganization() {
        return nameOrganization;
    }

    public void setNameOrganization(String nameOrganization) {
        this.nameOrganization = nameOrganization;
    }

    public String getNamePerson() {
        return namePerson;
    }

    public void setNamePerson(String namePerson) {
        this.namePerson = namePerson;
    }

    public int getId_organization_person() {
        return id_organization_person;
    }

    public void setId_organization_person(int id_organization_person) {
        this.id_organization_person = id_organization_person;
    }

    public int getId_organiztion() {
        return id_organiztion;
    }

    public void setId_organiztion(int id_organiztion) {
        this.id_organiztion = id_organiztion;
    }

    public int getId_Person() {
        return id_Person;
    }

    public void setId_Person(int id_Person) {
        this.id_Person = id_Person;
    }
}
