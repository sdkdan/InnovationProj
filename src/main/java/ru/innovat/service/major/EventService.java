package ru.innovat.service.major;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.major.EventDao;
import ru.innovat.models.major.Event;
import ru.innovat.models.utils.TypeEvent;

import java.util.List;


@Service
@RequiredArgsConstructor
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

    public List<Event> eventList() {
        return eventDao.eventList();
    }

    @Transactional
    public Event eventAllConnections(int id) {
        return eventDao.eventAllConnection(id);
    }

    public List<TypeEvent> findAllTypeEvents() {
        return eventDao.findAllTypeEvents();
    }
}
