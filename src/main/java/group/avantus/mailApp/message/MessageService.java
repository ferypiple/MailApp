package group.avantus.mailApp.message;

import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;

import java.util.Optional;

public interface MessageService  {
    Message createMessage(MailRecord mailRecord);




    Message updateStatus(Long messageId, Status status);


    Optional<Status> getMessageStatusById(Long id);

}