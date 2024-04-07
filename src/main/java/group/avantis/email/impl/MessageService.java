package group.avantis.email.impl;

import group.avantis.email.api.v0.MailRecord;
import group.avantis.email.exception.MessageNotSendException;
import group.avantis.email.impl.usecase.MessageCommand;
import group.avantis.email.impl.usecase.MessageQuery;
import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import group.avantis.email.repsitory.impl.jpa.MessageRepository;
import group.avantis.email.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    private final MessageRepository messageRepository;

    private final MessageQuery messageQuery;

    private final MessageCommand messageCommand;


    @Autowired
    public MessageService(MessageRepository messageRepository, MessageCommand messageCommand, MessageQuery messageQuery) {
        this.messageRepository = messageRepository;
        this.messageCommand = messageCommand;
        this.messageQuery = messageQuery;
    }

    public MessageResponse sendMessage(MailRecord mailRecord){
        try {
            Message messageEntity = Message.builder()
                    .from_email(mailRecord.from())
                    .to_email(mailRecord.to())
                    .status(Status.PENDING)
                    .subject(mailRecord.subject())
                    .text(mailRecord.text())
                    .send_date(LocalDateTime.now())
                    .build();
            saveMessage(messageEntity);

            return new MessageResponse(messageEntity.getId());
        }catch (Exception e){
            throw new MessageNotSendException();
        }

    }


    public Message saveMessage(Message message) {

        return messageCommand.execute(message);
    }

    public Message updateMessageStatus(Integer messageId, Status status) {
        return messageQuery.execute(messageId, status);
    }

    public Optional<Status> getMessageStatusById(Integer id) {
        return messageCommand.execute(id);
    }

    public List<Message> getMessages(Status status) {
       return messageQuery.execute(status);
    }
}

