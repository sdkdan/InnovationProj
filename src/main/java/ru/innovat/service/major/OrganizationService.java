package ru.innovat.service.major;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innovat.dao.major.OrganizationDao;
import ru.innovat.models.major.Organization;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {
    private final OrganizationDao organizationDao;

    public Organization findOrganization(int id) {
        return organizationDao.findById(id);
    }

    public void addOrganization(Organization organization) {
        organizationDao.add(organization);
    }

    public void deleteOrganization(int id) {
        organizationDao.delete(id);
    }

    public void updateOrganization(Organization organization) {
        organizationDao.update(organization);
    }

    public List<Organization> organizationList() {
        return organizationDao.organizationList();
    }

    public Organization organizationAllConnection(int id) {
        return organizationDao.organizationAllConnection(id);
    }
}
