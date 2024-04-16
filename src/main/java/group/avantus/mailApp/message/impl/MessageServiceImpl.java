package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.exception.MessageNotCreate;
import group.avantus.mailApp.message.file.FileService;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.email.exception.EmailSendException;
import group.avantus.mailApp.message.impl.usecase.query.GetMessageQuery;
import group.avantus.mailApp.message.impl.usecase.command.ChangeStatusCommand;
import group.avantus.mailApp.message.impl.usecase.command.SaveMessageCommand;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

  private final ChangeStatusCommand changeStatusCommand;

  private final GetMessageQuery getMessageQuery;
  private final SaveMessageCommand saveMessageCommand;

  private final FileService fileService;


  @Autowired
  public MessageServiceImpl(GetMessageQuery getMessageQuery,
      ChangeStatusCommand changeStatusCommand, SaveMessageCommand saveMessageCommand,
      FileServiceImpl fileService) {
    this.getMessageQuery = getMessageQuery;
    this.changeStatusCommand = changeStatusCommand;
    this.saveMessageCommand = saveMessageCommand;
    this.fileService = fileService;
  }

  @Transactional
  public Message saveMessage(MailRecord mailRecord) {
    try {
      var messageEntity = saveMessageCommand.execute(mailRecord);
      return messageEntity;
    } catch (Exception e) {
      throw new MessageNotCreate(e.getMessage());
    }

  }

  @Transactional
  public Message updateStatus(Long messageId, Status status) {
    return changeStatusCommand.execute(messageId, status);
  }

  @Transactional(readOnly = true)
  public Optional<Message> getMessage(Long id) {
    return getMessageQuery.execute(id);
  }
}

