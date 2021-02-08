package ru.innovat.service.support;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.innovat.dao.support.MessagesDao;
import ru.innovat.models.support.Messages;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MessagesService {

    private final MessagesDao messagesDao;

    public void addMessage(Messages messages) {
        messagesDao.add(messages);
    }

    public List<Messages> userMessages(int id) {
        return messagesDao.userMessages(id);
    }
}
