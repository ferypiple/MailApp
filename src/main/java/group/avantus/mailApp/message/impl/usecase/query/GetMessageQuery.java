package group.avantus.mailApp.message.impl.usecase.query;

import group.avantus.mailApp.message.exception.MessageNotFoundException;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetMessageQuery {

    private final MessageRepository messageRepository;

    @Autowired
    public GetMessageQuery(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message execute(Long id) {
        Message optionalMessage = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        return optionalMessage;
    }
}
