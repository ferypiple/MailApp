package group.avantus.mailApp.message.impl;

import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.exception.MessageNotCreate;
import group.avantus.mailApp.message.impl.usecase.command.ChangeStatusCommand;
import group.avantus.mailApp.message.impl.usecase.command.SaveMessageCommand;
import group.avantus.mailApp.message.impl.usecase.query.GetMessageQuery;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final ChangeStatusCommand changeStatusCommand;

    private final GetMessageQuery getMessageQuery;

    private final SaveMessageCommand saveMessageCommand;


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
    public Message getMessage(Long id) {
        return getMessageQuery.execute(id);
    }
}

