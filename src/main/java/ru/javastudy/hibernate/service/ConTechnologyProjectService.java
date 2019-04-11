package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ConTechnologyProjectDao;
import ru.javastudy.hibernate.models.ConTechnologyProject;

import java.util.List;


@Service
public class ConTechnologyProjectService {

    public ConTechnologyProjectService(){
    }

    @Autowired
    private ConTechnologyProjectDao conTechnologyProjectDao = new ConTechnologyProjectDao();

    @Transactional
    public ConTechnologyProject findConTechnologyProjectService(int id) {
        return conTechnologyProjectDao.findById(id);
    }

    @Transactional
    public List<ConTechnologyProject> conTechnologyPersonList() {
        return conTechnologyProjectDao.conTechnologyProjectList();
    }


}







