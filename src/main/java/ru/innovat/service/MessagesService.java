package ru.innovat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innovat.dao.MessagesDao;
import ru.innovat.models.Messages;

import java.util.List;

@Service
public class MessagesService {
    final
    MessagesDao messagesDao;

    public MessagesService(MessagesDao messagesDao) {
        this.messagesDao = messagesDao;
    }

    @Transactional
    public void addMessage(Messages messages){
        messagesDao.add(messages);
    }

    @Transactional
    public List<Messages> userMessages(int id){
        return messagesDao.userMessages(id);
    }
}
