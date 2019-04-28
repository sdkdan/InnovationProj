package ru.innovat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.OrganizationDao;
import ru.innovat.dao.PersonDao;
import ru.innovat.models.Organization;

import java.util.List;

@Service
public class OrganizationSevice {



    public OrganizationSevice(){

    }
    @Autowired
    private OrganizationDao organizationDao = new OrganizationDao();

    @Transactional
    public void setOrganizationDao(OrganizationDao organizationDao){
        this.organizationDao = organizationDao;
    }

    @Transactional
    public Organization findOrganization(int id) {
        return this.organizationDao.findById(id);
    }

    @Transactional
    public void addOrganization(Organization organization) {
        this.organizationDao.add(organization);
    }

    @Transactional
    public void deleteOrganiztion(int id) {
        this.organizationDao.delete(id);
    }

    @Transactional
    public void updateOrganization(Organization organizaion) {
        this.organizationDao.update(organizaion);
    }

    @Transactional
    public List<Organization> organizationList() {
        return this.organizationDao.organizationList();
    }
}
