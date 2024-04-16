package group.avantus.mailApp.message.impl.usecase.command;

import group.avantus.mailApp.message.file.FileService;
import group.avantus.mailApp.message.file.impl.usecase.command.SaveFileCommand;
import group.avantus.mailApp.message.impl.FileServiceImpl;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import group.avantus.mailApp.message.repository.impl.jpa.MessageRepository;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Component
public class SaveMessageCommand {

  private final MessageRepository messageRepository;

  private final FileService fileService;

  @Autowired
  public SaveMessageCommand(MessageRepository messageRepository, FileServiceImpl fileService) {
    this.messageRepository = messageRepository;
    this.fileService = fileService;
  }

  public Message execute(MailRecord mailRecord) throws IOException {
    List<FileEntity> files = new ArrayList<>();
    if (mailRecord.attachments() != null) {
      for (MultipartFile file : mailRecord.attachments()) {
        files.add(fileService.saveFile(file));
      }
    }
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
