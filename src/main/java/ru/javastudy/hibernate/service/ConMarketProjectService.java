package ru.javastudy.hibernate.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ConMarketProjectDao;
import ru.javastudy.hibernate.models.ConMarketProject;

import java.util.List;

public class ConMarketProjectService {
    public ConMarketProjectService(){

    }
    @Autowired
    private ConMarketProjectDao conMarketProjectDao = new ConMarketProjectDao();

    @Transactional
    public ConMarketProject findConMarketProjectService(int id) {
        return conMarketProjectDao.findById(id);
    }

    @Transactional
    public List<ConMarketProject> conMarketProjectList() {
        return conMarketProjectDao.conMarketProjectList();
    }

}
