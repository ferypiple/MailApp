package group.avantus.mailApp.impl.usecase;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class FindAllQuery {
    private final MessageRepository messageRepository;

    @Autowired
    public FindAllQuery(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public List<Message> execute(Status status) {
        return messageRepository.findAllByStatus(status);
    }
}
