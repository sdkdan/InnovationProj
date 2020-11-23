package ru.innovat.service;


import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.OrganizationDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.search.OrganizationSearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class OrganizationService {
    private final OrganizationDao organizationDao;

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

    @Transactional
    public Organization organizationAllConnection(int id){return organizationDao.organizationAllConnection(id);}

}
