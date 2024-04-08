package group.avantus.mailApp.email.api.v0;

import group.avantus.mailApp.email.api.v0.common.model.EmailRequest;
import group.avantus.mailApp.exception.CustomExceptionHandler;
import group.avantus.mailApp.email.impl.EmailServiceImpl;
import group.avantus.mailApp.message.impl.MessageServiceImpl;
import group.avantus.mailApp.email.model.MailRecord;
import group.avantus.mailApp.message.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CustomExceptionHandler
@RestController
@RequestMapping("/mailer/api/v0/mail")
public class MailController {

    private final EmailServiceImpl emailService;
    private final MessageServiceImpl messageServiceImpl;

    @Autowired
    public MailController(EmailServiceImpl emailService, MessageServiceImpl messageServiceImpl) {
        this.emailService = emailService;
        this.messageServiceImpl = messageServiceImpl;
    }
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getMessageStatus(@PathVariable("id") Integer messageId) {
        return ResponseEntity.ok().body("status: " + messageServiceImpl.getMessageStatusById(messageId).get());
    }
    @PostMapping
    public ResponseEntity<MessageResponse> sendEmailWithAttachment(EmailRequest emailRequest) {
        MailRecord mailRecord = new MailRecord(
                emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText(),
                emailRequest.getAttachments());
        return ResponseEntity.ok().body(new MessageResponse(messageServiceImpl.sendMessage(mailRecord).getId()));
    }
}
