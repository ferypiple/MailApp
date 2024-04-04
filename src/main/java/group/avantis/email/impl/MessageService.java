package group.avantis.email.impl;

import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import group.avantis.email.repsitory.MessageRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    public Message updateMessageStatus(Integer messageId, Status status) {
        Message message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("Message not found with id: " + messageId));
        message.setStatus(status);
        return messageRepository.save(message);
    }

    public Optional<Status> getMessageStatusById(Integer id) {
        Optional<Message> optionalMessage = messageRepository.findById(id);
        return optionalMessage.map(Message::getStatus);
    }

}
