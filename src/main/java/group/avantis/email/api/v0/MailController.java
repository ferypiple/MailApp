package group.avantis.email.api.v0;

import group.avantis.email.api.v0.common.model.EmailRequest;
import group.avantis.email.exception.CustomExceptionHandler;
import group.avantis.email.impl.EmailServiceImpl;
import group.avantis.email.impl.MessageService;
import group.avantis.email.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CustomExceptionHandler
@RestController
@RequestMapping("/mailer/api/v0/mail")
public class MailController {

    private final EmailServiceImpl emailService;
    private final MessageService messageService;

    @Autowired
    public MailController(EmailServiceImpl emailService,MessageService messageService) {
        this.emailService = emailService;
        this.messageService = messageService;
    }
    @GetMapping("/{id}/status")
    public ResponseEntity<String> getMessageStatus(@PathVariable("id") Integer messageId) {
        return ResponseEntity.ok().body("status: " + messageService.getMessageStatusById(messageId).get());
    }
    @PostMapping
    public ResponseEntity<MessageResponse> sendEmailWithAttachment(EmailRequest emailRequest) {
        return ResponseEntity.ok().body(new MessageResponse(messageService.sendMessage(new MailRecord(emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText(),
                emailRequest.getAttachments())).getId()));
    }
}
