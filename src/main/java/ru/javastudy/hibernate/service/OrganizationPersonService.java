package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.OrganizationPersonDao;
import ru.javastudy.hibernate.dao.PersonDao;
import ru.javastudy.hibernate.models.OrganizationPerson;

import java.util.List;

public class OrganizationPersonService {

    public OrganizationPersonService(){

    }
    @Autowired
    private OrganizationPersonDao organizationPersonDao = new OrganizationPersonDao();

    @Transactional
    public void setOrganizationPersonDao(PersonDao personDao){
        this.organizationPersonDao = organizationPersonDao;
    }

    @Transactional
    public OrganizationPerson findOrganizationPerson(int id) {
        return this.organizationPersonDao.findById(id);
    }

    @Transactional
    public void addOrganizationPerson(OrganizationPerson organizationPerson) {
        this.organizationPersonDao.add(organizationPerson);
    }

    @Transactional
    public void deleteOrganiztion(int id) {
        this.organizationPersonDao.delete(id);
    }

    @Transactional
    public List<OrganizationPerson> organizationList(int id) {
        return this.organizationPersonDao.organizationList(id);
    }

    @Transactional
    public List<OrganizationPerson> personList(int id) {
        return this.organizationPersonDao.personList(id);
    }
}
