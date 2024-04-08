package group.avantus.mailApp.impl.usecase.command;

import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveMessageCommand {

    private final MessageRepository messageRepository;

    @Autowired
    public SaveMessageCommand(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }
    public Message execute(Message message) {
        return messageRepository.save(message);
    }
}
