package group.avantus.mailApp.message;

import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;

public interface MessageService {

    Message saveMessage(MailRecord mailRecord);

    Message updateStatus(Long messageId, Status status);

    Message getMessage(Long id);

}
