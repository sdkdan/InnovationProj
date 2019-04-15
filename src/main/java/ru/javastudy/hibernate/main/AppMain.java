package ru.javastudy.hibernate.main;

import org.hibernate.Session;
import ru.javastudy.hibernate.models.Person;
import ru.javastudy.hibernate.models.Project;
import ru.javastudy.hibernate.service.ProjectService;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;
import ru.javastudy.hibernate.service.PersonService;
/**
 * Created by Nick on 05.09.2015.
 */
public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

//        Session session = HibernateSessionFactory.getSessionFactory().openSession();
//
//        session.beginTransaction();

//        Person contactEntity = new Person();
//
//        PersonService personService = new PersonService();
//
//        contactEntity.setName("Истинный");
//        contactEntity.setThirdName("Олд");
//        contactEntity.setSurname("(всегданаместе)");
//
//        personService.addPerson(contactEntity);


       Person person = new Person();

       PersonService personService = new PersonService();
        person = personService.findPerson(1);
       person.setComment("Худший в мире бд создатель (Dada ti)");
       personService.updatePerson(person);






//
//        personService.addPerson(contactEntity);

//        session.save(contactEntity);
//
//        session.getTransaction().commit();
//
//        session.close();


    }
}
