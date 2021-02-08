package ru.innovat.service.major;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.major.OrganizationDao;
import ru.innovat.models.major.Organization;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationDao organizationDao;

    @Transactional
    public Organization findOrganization(int id) {
        return organizationDao.findById(id);
    }

    @Transactional
    public void addOrganization(Organization organization) {
        organizationDao.add(organization);
    }

    @Transactional
    public void deleteOrganization(int id) {
        organizationDao.delete(id);
    }

    @Transactional
    public void updateOrganization(Organization organization) {
        organizationDao.update(organization);
    }

    public List<Organization> organizationList() {
        return organizationDao.organizationList();
    }

    @Transactional
    public Organization organizationAllConnection(int id) {
        return organizationDao.organizationAllConnection(id);
    }
}
