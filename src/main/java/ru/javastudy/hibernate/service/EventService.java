package ru.javastudy.hibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javastudy.hibernate.dao.EventDao;
import ru.javastudy.hibernate.models.Event;

import java.util.List;


@Service
public class EventService {

    public EventService(){

    }

    @Autowired
    private EventDao eventDao = new EventDao();

    @Transactional
    public void setEventDao(EventDao eventDao){
        this.eventDao = eventDao;
    }

    @Transactional
    public Event findEvent(int id) {
        return eventDao.findById(id);
    }

    @Transactional
    public void addEvent(Event event) {
        eventDao.add(event);
    }

    @Transactional
    public void deleteEvent(int id) {
        eventDao.delete(id);
    }

    @Transactional
    public void updateEvent(Event event) {
        eventDao.update(event);
    }

    @Transactional
    public List<Event> eventList() {
        return eventDao.eventList();
    }



}
