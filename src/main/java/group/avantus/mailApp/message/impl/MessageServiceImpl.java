package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.MessageService;
import group.avantus.mailApp.email.model.MailRecord;
import group.avantus.mailApp.exception.MessageNotSendException;
import group.avantus.mailApp.impl.usecase.query.GetMessageQuery;
import group.avantus.mailApp.impl.usecase.command.ChangeStatusCommand;
import group.avantus.mailApp.impl.usecase.command.SaveCommand;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final ChangeStatusCommand changeStatusCommand;

    private final GetMessageQuery getMessageQuery;
    private final SaveCommand saveCommand;




    @Autowired
    public MessageServiceImpl(GetMessageQuery getMessageQuery, ChangeStatusCommand changeStatusCommand, SaveCommand saveCommand) {
        this.getMessageQuery = getMessageQuery;
        this.changeStatusCommand = changeStatusCommand;
        this.saveCommand = saveCommand;
    }

    public Message sendMessage(MailRecord mailRecord){
        try {
            Message messageEntity = Message.builder()
                    .from_email(mailRecord.from())
                    .to_email(mailRecord.to())
                    .status(Status.PENDING)
                    .subject(mailRecord.subject())
                    .text(mailRecord.text())
                    .send_date(LocalDateTime.now())
                    .build();
            saveCommand.execute(messageEntity);

            return messageEntity;
        }catch (Exception e){
            throw new MessageNotSendException();
        }

    }

    public Message updateMessageStatus(Long messageId, Status status) {
        return changeStatusCommand.execute(messageId, status);
    }

    public Optional<Status> getMessageStatusById(Long id) {
        return getMessageQuery.execute(id);
    }
}

