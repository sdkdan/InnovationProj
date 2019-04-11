package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ConTechnologyOrganizationDao;
import ru.javastudy.hibernate.models.ConTechnologyOrganization;

import java.util.List;


@Service
public class ConTehcnologyOrganizationService {

    public ConTehcnologyOrganizationService(){
    }

    @Autowired
    private ConTechnologyOrganizationDao conTechnologyOrganizationDao = new ConTechnologyOrganizationDao();

    @Transactional
    public ConTechnologyOrganization findConTechnologyOrganization(int id) {
        return conTechnologyOrganizationDao.findById(id);
    }

    @Transactional
    public List<ConTechnologyOrganization> conTechnologyOrganizationList() {
        return conTechnologyOrganizationDao.conTechnologyOrganizationList();
    }


}






