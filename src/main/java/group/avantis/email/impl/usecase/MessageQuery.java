package group.avantis.email.impl.usecase;

import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import group.avantis.email.repsitory.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageQuery {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageQuery(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message execute(Integer messageId, Status status) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));
        message.setStatus(status);
        return messageRepository.save(message);
    }
    public List<Message> execute(Status status) {
        return messageRepository.findAllByStatus(status);
    }
}
