package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.MessageService;
import group.avantus.mailApp.email.model.MailRecord;
import group.avantus.mailApp.exception.MessageNotSendException;
import group.avantus.mailApp.impl.usecase.FindCommand;
import group.avantus.mailApp.impl.usecase.ChangeStatusQuery;
import group.avantus.mailApp.impl.usecase.SaveCommand;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final ChangeStatusQuery changeStatusQuery;

    private final FindCommand findCommand;
    private final SaveCommand saveCommand;




    @Autowired
    public MessageServiceImpl(FindCommand findCommand, ChangeStatusQuery changeStatusQuery, SaveCommand saveCommand) {
        this.findCommand = findCommand;
        this.changeStatusQuery = changeStatusQuery;
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

    public Message updateMessageStatus(Integer messageId, Status status) {
        return changeStatusQuery.execute(messageId, status);
    }

    public Optional<Status> getMessageStatusById(Integer id) {
        return findCommand.execute(id);
    }
}

