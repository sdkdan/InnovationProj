package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ConTechnologyPersonDao;
import ru.javastudy.hibernate.models.ConTechnologyPerson;

import java.util.List;


@Service
public class ConTehcnologyPersonService {

    public ConTehcnologyPersonService(){
    }

    @Autowired
    private ConTechnologyPersonDao conTechnologyPersonDao = new ConTechnologyPersonDao();

    @Transactional
    public ConTechnologyPerson findConTechnologyPerson(int id) {
        return conTechnologyPersonDao.findById(id);
    }

    @Transactional
    public List<ConTechnologyPerson> conTechnologyPersonList() {
        return conTechnologyPersonDao.conTechnologyPersonList();
    }


}



