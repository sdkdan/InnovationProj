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
import ru.innovat.models.major.Project;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.client.ExpectedCount.times;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"create-organization-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource("../../../../resources/application-test.properties")
@Transactional
public class OrganizationServiceWithBDTest {
    @Autowired
    ProjectService projectService;
    @Autowired
    EventService eventService;
    @Autowired
    PersonService personService;
    @Autowired
    OrganizationService organizationService;


    @Test
    public void testFindByIdWithBD() {
        Organization organization = organizationService.findOrganization(1);
        assertThat(organization.getId_organization()).isEqualTo(1);
        assertThat(organization.getName_organization()).isEqualTo("test1");
        assertThat(organization.getCity_organization()).isEqualTo("Nursultan");
        assertThat(organization.getSite_organization()).isEqualTo("spbbu@kek.ru");
        assertThat(organization.getNotes_organization()).isEqualTo("DFDFD");
    }

    @Test
    public void organizationListTest() {
        List<Organization> organizationList = organizationService.organizationList();
        assertThat(organizationList.get(0).getId_organization()).isEqualTo(1);
        assertThat(organizationList.get(0).getName_organization()).isEqualTo("test1");
        assertThat(organizationList.get(0).getCity_organization()).isEqualTo("Nursultan");
        assertThat(organizationList.get(0).getSite_organization()).isEqualTo("spbbu@kek.ru");
        assertThat(organizationList.get(0).getNotes_organization()).isEqualTo("DFDFD");
        assertThat(organizationList.get(1).getId_organization()).isEqualTo(2);
        assertThat(organizationList.get(1).getName_organization()).isEqualTo("test2");
        assertThat(organizationList.get(1).getCity_organization()).isEqualTo("Nursultan");
        assertThat(organizationList.get(1).getSite_organization()).isEqualTo("spbbu1@kek.ru");
        assertThat(organizationList.get(1).getNotes_organization()).isEqualTo("DFDFD");
    }


    @Test
    public void updateOrganizationTest() {
        Organization organization = organizationService.findOrganization(1);
        organization.setCity_organization("testCity");
        organization.setName_organization("testName");
        organizationService.updateOrganization(organization);
        Organization updatedOrganization = organizationService.findOrganization(1);
        assertThat(organization.getId_organization()).isEqualTo(updatedOrganization.getId_organization());
        assertThat(organization.getName_organization()).isEqualTo(updatedOrganization.getName_organization());
        assertThat(organization.getCity_organization()).isEqualTo(updatedOrganization.getCity_organization());
        assertThat(organization.getSite_organization()).isEqualTo(updatedOrganization.getSite_organization());
        assertThat(organization.getNotes_organization()).isEqualTo(updatedOrganization.getNotes_organization());
    }

//    @Test
//    public void  addProjectTest(){
//        Organization organization = new Organization();
//        organization.setCity_organization("testCity");
//        organization.setName_organization("testName");
//        organization.setNotes_organization("testNotes");
//        organization.setSite_organization("site");
//        organizationService.addOrganization(organization);
//        verify(organizationService,times(1)).addOrganization(organization);
//        Organization addedOrganization = organizationService.findOrganization(2);
//        assertThat(organization.getId_organization()).isEqualTo(addedOrganization.getId_organization());
//        assertThat(organization.getName_organization()).isEqualTo(addedOrganization.getName_organization());
//        assertThat(organization.getCity_organization()).isEqualTo(addedOrganization.getCity_organization());
//        assertThat(organization.getSite_organization()).isEqualTo(addedOrganization.getSite_organization());
//        assertThat(organization.getNotes_organization()).isEqualTo(addedOrganization.getNotes_organization());
//    }


}


