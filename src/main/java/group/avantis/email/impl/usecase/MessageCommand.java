package group.avantis.email.impl.usecase;

import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import group.avantis.email.repsitory.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessageCommand {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageCommand(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Optional<Status> execute(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.map(Message::getStatus);
    }

    public Message execute(Message message) {
        return messageRepository.save(message);
    }
}
