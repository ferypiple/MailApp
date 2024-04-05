package group.avantis.email.api.v0;

import group.avantis.email.api.v0.model.EmailRequest;
import group.avantis.email.exceptions.CustomExceptionHandler;
import group.avantis.email.impl.EmailServiceImpl;
import group.avantis.email.impl.MessageService;
import group.avantis.email.model.Message;
import group.avantis.email.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
@CustomExceptionHandler
@RestController
@RequestMapping("/mailer/api/v0/mail")
public class MailController {

    private final EmailServiceImpl emailServiceImpl;
    private final MessageService messageService;

    @Autowired
    public MailController(EmailServiceImpl emailServiceImpl, MessageService messageService) {
        this.emailServiceImpl = emailServiceImpl;
        this.messageService = messageService;
    }


    @GetMapping("/{id}/status")
    public ResponseEntity<String> getMessageStatus(@PathVariable("id") Integer messageId) {
        return ResponseEntity.ok().body("status: " + messageService.getMessageStatusById(messageId).get());
    }


    @PostMapping
    public ResponseEntity<MessageResponse> sendEmailWithAttachment(
            @ModelAttribute EmailRequest emailRequest) {
        MailRecord mailRecord = new MailRecord(emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText(),
                emailRequest.getAttachments());
        CompletableFuture<Message> future = CompletableFuture.supplyAsync(() ->
                emailServiceImpl.sendEmail(mailRecord));
        return ResponseEntity.ok().body(new MessageResponse(future.join().getId()));
    }


}
