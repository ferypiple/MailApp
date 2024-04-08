package group.avantus.mailApp.impl.usecase.query;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class GetMessageQuery {
    private final MessageRepository messageRepository;

    @Autowired
    public GetMessageQuery(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<Status> execute(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.map(Message::getStatus);
    }
}
