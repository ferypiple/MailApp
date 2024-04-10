package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.email.exception.MessageNotSendException;
import group.avantus.mailApp.message.impl.usecase.query.GetMessageQuery;
import group.avantus.mailApp.message.impl.usecase.command.ChangeStatusCommand;
import group.avantus.mailApp.message.impl.usecase.command.SaveMessageCommand;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    private final FileServiceImpl fileServiceImpl;


    @Autowired
    public MessageServiceImpl(GetMessageQuery getMessageQuery, ChangeStatusCommand changeStatusCommand, SaveMessageCommand saveMessageCommand, FileServiceImpl fileServiceImpl) {
        this.getMessageQuery = getMessageQuery;
        this.changeStatusCommand = changeStatusCommand;
        this.saveMessageCommand = saveMessageCommand;
        this.fileServiceImpl = fileServiceImpl;
    }

    public Message createMessage(MailRecord mailRecord) {
        Message messageEntity = null;
        try {
            List<FileEntity> files = new ArrayList<>();
            if (mailRecord.attachments() != null) {
                for (MultipartFile file : mailRecord.attachments()) {
                    files.add(fileServiceImpl.saveFile(file));
                }
            }
             messageEntity = Message.builder()
                    .from_email(mailRecord.from())
                    .to_email(mailRecord.to())
                    .status(Status.PENDING)
                    .subject(mailRecord.subject())
                    .text(mailRecord.text())
                    .send_date(LocalDateTime.now())
                    .files(files)
                    .build();
            saveMessageCommand.execute(messageEntity);

            return messageEntity;
        } catch (Exception e) {
            messageEntity.setStatus(Status.ERROR);
            throw new MessageNotSendException();
        }

    }

    public Message updateStatus(Long messageId, Status status) {
        return changeStatusCommand.execute(messageId, status);
    }

    public Optional<Status> getMessageStatusById(Long id) {
        return getMessageQuery.execute(id);
    }
}

