package group.avantus.mailApp;

import group.avantus.mailApp.email.model.MailRecord;
import group.avantus.mailApp.exception.MessageNotSendException;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MessageService  {
    Message sendMessage(MailRecord mailRecord);




    Message updateMessageStatus(Integer messageId, Status status);


    Optional<Status> getMessageStatusById(Integer id);

}
