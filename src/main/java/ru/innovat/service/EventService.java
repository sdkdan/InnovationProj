package ru.innovat.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.EventDao;
import ru.innovat.models.Event;
import ru.innovat.models.Organization;
import ru.innovat.models.Person;
import ru.innovat.models.Project;
import ru.innovat.models.utils.Connect;
import ru.innovat.models.utils.TypeEvent;
import ru.innovat.search.EventSearch;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@AllArgsConstructor(onConstructor=@__({@Lazy}))
public class EventService {
    private final EventDao eventDao;

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

    @Transactional
    public Event eventAllConnections(int id) {
        return eventDao.eventAllConnection(id);
    }

    @Transactional
    public TypeEvent findTypeEventById(int id) {
        return eventDao.findTypeEventById(id);
    }

    @Transactional
    public List<TypeEvent> findAllTypeEvents() {
        return eventDao.findAllTypeEvents();
    }


}
