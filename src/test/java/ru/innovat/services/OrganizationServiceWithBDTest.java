package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.major.Organization;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

public class OrganizationServiceWithBDTest extends ConfigServiceTest {

    @Test
    public void testFindByIdWithBD() {
        Organization organization = organizationService.findOrganization(1);
        assertThat(organization.getId_organization()).isEqualTo(1);
        assertThat(organization.getNameOrganization()).isEqualTo("test1");
        assertThat(organization.getCityOrganization()).isEqualTo("Nursultan");
        assertThat(organization.getSiteOrganization()).isEqualTo("spbbu@kek.ru");
        assertThat(organization.getNotesOrganization()).isEqualTo("DFDFD");
    }

    @Test
    public void organizationListTest() {
        List<Organization> organizationList = organizationService.organizationList();
        assertThat(organizationList.get(0).getId_organization()).isEqualTo(1);
        assertThat(organizationList.get(0).getNameOrganization()).isEqualTo("test1");
        assertThat(organizationList.get(0).getCityOrganization()).isEqualTo("Nursultan");
        assertThat(organizationList.get(0).getSiteOrganization()).isEqualTo("spbbu@kek.ru");
        assertThat(organizationList.get(0).getNotesOrganization()).isEqualTo("DFDFD");
        assertThat(organizationList.get(1).getId_organization()).isEqualTo(2);
        assertThat(organizationList.get(1).getNameOrganization()).isEqualTo("test2");
        assertThat(organizationList.get(1).getCityOrganization()).isEqualTo("Nursultan");
        assertThat(organizationList.get(1).getSiteOrganization()).isEqualTo("spbbu1@kek.ru");
        assertThat(organizationList.get(1).getNotesOrganization()).isEqualTo("DFDFD");
    }

    @Test
    public void updateOrganizationTest() {
        Organization organization = organizationService.findOrganization(1);
        organization.setCityOrganization("testCity");
        organization.setNameOrganization("testName");
        organizationService.updateOrganization(organization);
        Organization updatedOrganization = organizationService.findOrganization(1);
        assertThat(organization.getId_organization()).isEqualTo(updatedOrganization.getId_organization());
        assertThat(organization.getNameOrganization()).isEqualTo(updatedOrganization.getNameOrganization());
        assertThat(organization.getCityOrganization()).isEqualTo(updatedOrganization.getCityOrganization());
        assertThat(organization.getSiteOrganization()).isEqualTo(updatedOrganization.getSiteOrganization());
        assertThat(organization.getNotesOrganization()).isEqualTo(updatedOrganization.getNotesOrganization());
    }

}


