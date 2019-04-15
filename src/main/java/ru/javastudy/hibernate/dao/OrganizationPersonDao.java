package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javastudy.hibernate.models.*;

import java.util.List;


@Repository
public class OrganizationPersonDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public OrganizationPerson findById(int id){
        Session session = this.sessionFactory.getCurrentSession();
        //Загружаем таблицу связи
        OrganizationPerson organizationPerson = (OrganizationPerson) session.load(OrganizationPerson.class, id);
        //Создаем дао персоны и организации для дальнейшей работы
        PersonDao personDao = new PersonDao();
        OrganizationDao organizationDao = new OrganizationDao();
        //Ищем персоны по id из таблицы связи и записываем в обект типа Person
        Person person = personDao.findById(organizationPerson.getId_Person());
        //Записываем имя фамилию и отчество в таблицу связи
        organizationPerson.setNameOrganization(person.getName());
        organizationPerson.setSurnamePerson(person.getSurname());
        organizationPerson.setThridnamePerson(person.getThirdName());
        //Ищем организацию по id из таблицы связи
        Organization organization = organizationDao.findById(organizationPerson.getId_organiztion());
        //Записываем название организации в таблицу связи
        organizationPerson.setNameOrganization(organization.getName_organization());
        return organizationPerson;
    }

    public void add(OrganizationPerson organizationPerson){
        Session session = sessionFactory.getCurrentSession();
        session.persist(organizationPerson);
    }


    public void delete(int id){
        Session session = sessionFactory.getCurrentSession();
        OrganizationPerson organizationPerson = (OrganizationPerson)session.load(OrganizationPerson.class, id);

        if(organizationPerson != null){
            session.delete(organizationPerson);
        }
    }


    @SuppressWarnings("unchecked")
    public List<OrganizationPerson> organizationList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        OrganizationDao organizationDao = new OrganizationDao();
        //Задаем запрос к Hibernate который выделяет все строчик связанные с определенной персоной и заносит их в лист
        List<OrganizationPerson> organizationPersonList = (List<OrganizationPerson>) session.createQuery("From OrganizationPerson organizationPerson Where organizationPerson.id_person = " + id).list();
        //Добавляем в связь
        for(OrganizationPerson organizationPerson:organizationPersonList){
            Organization organization = organizationDao.findById(organizationPerson.getId_organiztion());
            organizationPerson.setNameOrganization(organization.getName_organization());
        }
        return organizationPersonList;

    }

    @SuppressWarnings("unchecked")
    public List<OrganizationPerson> personList(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        PersonDao personDao = new PersonDao();
        List<OrganizationPerson> organizationPersonList = (List<OrganizationPerson>) session.createQuery("From OrganizationPerson organizationPerson Where organizationPerson.id_organization = " + id).list();
        for(OrganizationPerson organizationPerson:organizationPersonList){
            Person person = personDao.findById(organizationPerson.getId_Person());
            organizationPerson.setNameOrganization(person.getName());
            organizationPerson.setSurnamePerson(person.getSurname());
            organizationPerson.setThridnamePerson(person.getThirdName());
        }
        return organizationPersonList;

    }

}
