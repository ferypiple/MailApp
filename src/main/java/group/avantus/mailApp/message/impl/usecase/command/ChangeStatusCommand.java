package group.avantus.mailApp.message.impl.usecase.command;

import group.avantus.mailApp.message.exception.MessageNotFoundException;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChangeStatusCommand {

  private final MessageRepository messageRepository;

  @Autowired
  public ChangeStatusCommand(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message execute(Long messageId, Status status) {
    Message message = messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException(messageId));
    message.setStatus(status);
    return messageRepository.save(message);
  }

}
