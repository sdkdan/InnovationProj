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


public class PersonServiceWithBDTest extends ConfigServiceTest {

    @Test
    public void testFindByIdWithBD() {
        Person person = personService.findPerson(1);
        assertThat(person.getId_person()).isEqualTo(1);
        assertThat(person.getName()).isEqualTo("test");
        assertThat(person.getSurname()).isEqualTo("test");
        assertThat(person.getThirdName()).isEqualTo("test");
        assertThat(person.getComment()).isEqualTo("комментарий");
        assertThat(person.getDateOfBirth()).isEqualTo("1997-28-02");
        assertThat(person.getEMail()).isEqualTo("S@geroyam.slava");
        assertThat(person.getRating()).isEqualTo("1");
        assertThat(person.getVk()).isEqualTo("ВК");
        assertThat(person.getPhoneNumber()).isEqualTo("88005553535");
        assertThat(person.getPhoneNumber()).isEqualTo("88005553535");
        assertThat(person.getTwitter()).isEqualTo("Твитер");
    }

    @Test
    public void projectListTest() {
        List<Person> personList = personService.personList();
        assertThat(personList.get(0).getId_person()).isEqualTo(1);
        assertThat(personList.get(0).getName()).isEqualTo("test");
        assertThat(personList.get(0).getSurname()).isEqualTo("test");
        assertThat(personList.get(0).getThirdName()).isEqualTo("test");
        assertThat(personList.get(0).getComment()).isEqualTo("комментарий");
        assertThat(personList.get(0).getDateOfBirth()).isEqualTo("1997-28-02");
        assertThat(personList.get(0).getEMail()).isEqualTo("S@geroyam.slava");
        assertThat(personList.get(0).getRating()).isEqualTo("1");
        assertThat(personList.get(0).getVk()).isEqualTo("ВК");
        assertThat(personList.get(0).getPhoneNumber()).isEqualTo("88005553535");
        assertThat(personList.get(1).getTwitter()).isEqualTo("Твитер");
        assertThat(personList.get(1).getId_person()).isEqualTo(2);
        assertThat(personList.get(1).getName()).isEqualTo("test");
        assertThat(personList.get(1).getSurname()).isEqualTo("test");
        assertThat(personList.get(1).getThirdName()).isEqualTo("test");
        assertThat(personList.get(1).getComment()).isEqualTo("комментарий");
        assertThat(personList.get(1).getDateOfBirth()).isEqualTo("1997-28-02");
        assertThat(personList.get(1).getEMail()).isEqualTo("S@geroyam.slava");
        assertThat(personList.get(1).getRating()).isEqualTo("1");
        assertThat(personList.get(1).getVk()).isEqualTo("ВК");
        assertThat(personList.get(1).getPhoneNumber()).isEqualTo("88005553535");
        assertThat(personList.get(1).getTwitter()).isEqualTo("Твитер");
    }

    @Test
    public void updateProjectTest() {
        Person person = personService.findPerson(1);
        person.setName("testName");
        person.setSurname("testSurname");
        personService.updatePerson(person);
        Person updatedPerson = personService.findPerson(1);
        assertThat(person.getId_person()).isEqualTo(updatedPerson.getId_person());
        assertThat(person.getName()).isEqualTo(updatedPerson.getName());
        assertThat(person.getSurname()).isEqualTo(updatedPerson.getSurname());
        assertThat(person.getThirdName()).isEqualTo(updatedPerson.getThirdName());
        assertThat(person.getComment()).isEqualTo(updatedPerson.getComment());
        assertThat(person.getDateOfBirth()).isEqualTo(updatedPerson.getDateOfBirth());
        assertThat(person.getEMail()).isEqualTo(updatedPerson.getEMail());
        assertThat(person.getRating()).isEqualTo(updatedPerson.getRating());
        assertThat(person.getVk()).isEqualTo(updatedPerson.getVk());
        assertThat(person.getPhoneNumber()).isEqualTo(updatedPerson.getPhoneNumber());
        assertThat(person.getTwitter()).isEqualTo(updatedPerson.getTwitter());
    }

    @Test
    public void addPersonTest() {
        Person person = new Person();
        person.setName("test");
        person.setSurname("test");
        person.setThirdName("test");
        person.setComment("комментарий");
        person.setDateOfBirth("1997-28-02");
        person.setEMail("S@geroyam.slava");
        person.setRating("1");
        person.setVk("ВК");
        person.setPhoneNumber("88005553535");
        person.setTwitter("Твитер");
        personService.addPerson(person);
        verify(personService, times(1)).addPerson(person);
        Person addedPerson = personService.findPerson(15);
        assertThat(person.getId_person()).isEqualTo(addedPerson.getId_person());
        assertThat(person.getName()).isEqualTo(addedPerson.getName());
        assertThat(person.getSurname()).isEqualTo(addedPerson.getSurname());
        assertThat(person.getThirdName()).isEqualTo(addedPerson.getThirdName());
        assertThat(person.getComment()).isEqualTo(addedPerson.getComment());
        assertThat(person.getDateOfBirth()).isEqualTo(addedPerson.getDateOfBirth());
        assertThat(person.getEMail()).isEqualTo(addedPerson.getEMail());
        assertThat(person.getRating()).isEqualTo(addedPerson.getRating());
        assertThat(person.getVk()).isEqualTo(addedPerson.getVk());
        assertThat(person.getPhoneNumber()).isEqualTo(addedPerson.getPhoneNumber());
        assertThat(person.getTwitter()).isEqualTo(addedPerson.getTwitter());
    }

}
