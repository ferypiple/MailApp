package group.avantus.mailApp.message.impl.usecase.command;

import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class SaveMessageCommand {

  private final MessageRepository messageRepository;

  @Autowired
  public SaveMessageCommand(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message execute(MailRecord mailRecord, List<FileEntity> files) {

    Message messageEntity = Message.builder()
        .from_email(mailRecord.from())
        .to_email(mailRecord.to())
        .status(Status.PENDING)
        .subject(mailRecord.subject())
        .text(mailRecord.text())
        .send_date(LocalDateTime.now())
        .files(files)
        .build();

    return messageRepository.save(messageEntity);
  }
}
