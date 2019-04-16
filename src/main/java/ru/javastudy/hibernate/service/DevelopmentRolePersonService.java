package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.DevelopmentRolePersonDao;
import ru.javastudy.hibernate.models.DevelopmentRolePerson;

import java.util.List;
@Service
public class DevelopmentRolePersonService {

    public DevelopmentRolePersonService(){

    }

    @Autowired
    private DevelopmentRolePersonDao developmentRolePersonDao = new DevelopmentRolePersonDao();

    @Transactional
    public void setDevelopmentRolePersonDao(DevelopmentRolePerson DevelopmentRolePerson){
        this.developmentRolePersonDao = developmentRolePersonDao;
    }

    @Transactional
    public DevelopmentRolePerson findDevelopmentRolePerson(int id) {
        return developmentRolePersonDao.findById(id);
    }

    @Transactional
    public void addDevelopmentRolePerson(DevelopmentRolePerson developmentRolePerson) {
        developmentRolePersonDao.add(developmentRolePerson);
    }

    @Transactional
    public void deleteDevelopmentRolePerson(int id) {
        developmentRolePersonDao.delete(id);
    }

    @Transactional
    public void updateDevelopmentRolePerson(DevelopmentRolePerson developmentRolePerson) {
        developmentRolePersonDao.update(developmentRolePerson);
    }

    @Transactional
    public List<DevelopmentRolePerson> developmentRolePersonList() {
        return developmentRolePersonDao.developmentRolePersonList();
    }
}
