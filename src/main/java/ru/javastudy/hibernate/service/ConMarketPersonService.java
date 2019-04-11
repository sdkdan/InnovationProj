package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.ConMarketPersonDao;
import ru.javastudy.hibernate.models.ConMarketPerson;

import java.util.List;


@Service
public class ConMarketPersonService {

    public ConMarketPersonService(){
    }

    @Autowired
    private ConMarketPersonDao conMarketPersonDao = new ConMarketPersonDao();

    @Transactional
    public ConMarketPerson findConMarketPersonService(int id) {
        return conMarketPersonDao.findById(id);
    }

    @Transactional
    public List<ConMarketPerson> conMarketPersonList() {
        return conMarketPersonDao.conMarketPersonList();
    }


}









