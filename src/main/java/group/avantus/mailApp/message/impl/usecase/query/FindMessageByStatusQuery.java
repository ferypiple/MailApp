package group.avantus.mailApp.message.impl.usecase.query;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FindMessageByStatusQuery {

    private final MessageRepository messageRepository;

    @Autowired
    public FindMessageByStatusQuery(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> execute(Status status) {
        return messageRepository.findAllByStatus(status);
    }
}
