package group.avantus.mailApp.message.api.v0;

import group.avantus.mailApp.message.api.v0.common.model.EmailRequest;
import group.avantus.mailApp.exception.CustomExceptionHandler;
import group.avantus.mailApp.message.impl.MessageServiceImpl;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.api.v0.common.model.MessageResponse;
import group.avantus.mailApp.message.api.v0.common.model.StatusResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CustomExceptionHandler
@RestController
@RequestMapping("/mailapp/api/v0/mail")
public class MailController {

    private final MessageServiceImpl messageServiceImpl;

    @Autowired
    public MailController(MessageServiceImpl messageServiceImpl) {
        this.messageServiceImpl = messageServiceImpl;
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/status")
    public StatusResponse getMessageStatus(@PathVariable("id") Long messageId) {
         return new StatusResponse(messageServiceImpl.getMessageStatusById(messageId).get());
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping
    public MessageResponse addMessage(@ModelAttribute EmailRequest emailRequest) {
        MailRecord mailRecord = new MailRecord(
                emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText(),
                emailRequest.getAttachments());
        return new MessageResponse(messageServiceImpl.createMessage(mailRecord).getId());
    }
}
