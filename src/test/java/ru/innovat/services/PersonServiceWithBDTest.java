package ru.innovat.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.models.major.Person;
import ru.innovat.service.major.EventService;
import ru.innovat.service.major.OrganizationService;
import ru.innovat.service.major.PersonService;
import ru.innovat.service.major.ProjectService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(value = {"/sql/create-person-before.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@TestPropertySource("/application-test.properties")
@Transactional
public class PersonServiceWithBDTest {

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
        Person person = personService.findPerson(1);
        assertThat(person.getId_person()).isEqualTo(1);
        assertThat(person.getName()).isEqualTo("test");
        assertThat(person.getSurname()).isEqualTo("test");
        assertThat(person.getThird_Name()).isEqualTo("test");
        assertThat(person.getComment()).isEqualTo("комментарий");
        assertThat(person.getDate_of_birth()).isEqualTo("1997-28-02");
        assertThat(person.getE_mail()).isEqualTo("S@geroyam.slava");
        assertThat(person.getRating()).isEqualTo("1");
        assertThat(person.getVk()).isEqualTo("ВК");
        assertThat(person.getPhone_number()).isEqualTo("88005553535");
        assertThat(person.getTwitter()).isEqualTo("Твитер");
    }

    @Test
    public void projectListTest(){
        List<Person> personList = personService.personList();
        assertThat(personList.get(0).getId_person()).isEqualTo(1);
        assertThat(personList.get(0).getName()).isEqualTo("test");
        assertThat(personList.get(0).getSurname()).isEqualTo("test");
        assertThat(personList.get(0).getThird_Name()).isEqualTo("test");
        assertThat(personList.get(0).getComment()).isEqualTo("комментарий");
        assertThat(personList.get(0).getDate_of_birth()).isEqualTo("1997-28-02");
        assertThat(personList.get(0).getE_mail()).isEqualTo("S@geroyam.slava");
        assertThat(personList.get(0).getRating()).isEqualTo("1");
        assertThat(personList.get(0).getVk()).isEqualTo("ВК");
        assertThat(personList.get(0).getPhone_number()).isEqualTo("88005553535");
        assertThat(personList.get(1).getTwitter()).isEqualTo("Твитер");
        assertThat(personList.get(1).getId_person()).isEqualTo(2);
        assertThat(personList.get(1).getName()).isEqualTo("test");
        assertThat(personList.get(1).getSurname()).isEqualTo("test");
        assertThat(personList.get(1).getThird_Name()).isEqualTo("test");
        assertThat(personList.get(1).getComment()).isEqualTo("комментарий");
        assertThat(personList.get(1).getDate_of_birth()).isEqualTo("1997-28-02");
        assertThat(personList.get(1).getE_mail()).isEqualTo("S@geroyam.slava");
        assertThat(personList.get(1).getRating()).isEqualTo("1");
        assertThat(personList.get(1).getVk()).isEqualTo("ВК");
        assertThat(personList.get(1).getPhone_number()).isEqualTo("88005553535");
        assertThat(personList.get(1).getTwitter()).isEqualTo("Твитер");
    }

    @Test
    public void updateProjectTest(){
        Person person = personService.findPerson(1);
        person.setName("testName");
        person.setSurname("testSurname");
        personService.updatePerson(person);
        Person updatedPerson = personService.findPerson(1);
        assertThat(person.getId_person()).isEqualTo(updatedPerson.getId_person());
        assertThat(person.getName()).isEqualTo(updatedPerson.getName());
        assertThat(person.getSurname()).isEqualTo(updatedPerson.getSurname());
        assertThat(person.getThird_Name()).isEqualTo(updatedPerson.getThird_Name());
        assertThat(person.getComment()).isEqualTo(updatedPerson.getComment());
        assertThat(person.getDate_of_birth()).isEqualTo(updatedPerson.getDate_of_birth());
        assertThat(person.getE_mail()).isEqualTo(updatedPerson.getE_mail());
        assertThat(person.getRating()).isEqualTo(updatedPerson.getRating());
        assertThat(person.getVk()).isEqualTo(updatedPerson.getVk());
        assertThat(person.getPhone_number()).isEqualTo(updatedPerson.getPhone_number());
        assertThat(person.getTwitter()).isEqualTo(updatedPerson.getTwitter());
    }

    @Test
    public void  addPersonTest(){
        Person person = new Person();
        person.setName("test");
        person.setSurname("test");
        person.setThird_Name("test");
        person.setComment("комментарий");
        person.setDate_of_birth("1997-28-02");
        person.setE_mail("S@geroyam.slava");
        person.setRating("1");
        person.setVk("ВК");
        person.setPhone_number("88005553535");
        person.setTwitter("Твитер");
        personService.addPerson(person);
        verify(personService,times(1)).addPerson(person);
        Person addedPerson = personService.findPerson(15);
        assertThat(person.getId_person()).isEqualTo(addedPerson.getId_person());
        assertThat(person.getName()).isEqualTo(addedPerson.getName());
        assertThat(person.getSurname()).isEqualTo(addedPerson.getSurname());
        assertThat(person.getThird_Name()).isEqualTo(addedPerson.getThird_Name());
        assertThat(person.getComment()).isEqualTo(addedPerson.getComment());
        assertThat(person.getDate_of_birth()).isEqualTo(addedPerson.getDate_of_birth());
        assertThat(person.getE_mail()).isEqualTo(addedPerson.getE_mail());
        assertThat(person.getRating()).isEqualTo(addedPerson.getRating());
        assertThat(person.getVk()).isEqualTo(addedPerson.getVk());
        assertThat(person.getPhone_number()).isEqualTo(addedPerson.getPhone_number());
        assertThat(person.getTwitter()).isEqualTo(addedPerson.getTwitter());
    }


}
