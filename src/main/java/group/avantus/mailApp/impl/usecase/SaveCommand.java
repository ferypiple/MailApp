package group.avantus.mailApp.impl.usecase;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveCommand {

    private final MessageRepository messageRepository;

    @Autowired
    public SaveCommand(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public Message execute(Message message) {
        return messageRepository.save(message);
    }
}
