package group.avantis.email.api.v0;

import group.avantis.email.api.v0.model.EmailRequest;
import group.avantis.email.exceptions.CustomExceptionHandler;
import group.avantis.email.impl.EmailServiceImpl;
import group.avantis.email.impl.MessageService;
import group.avantis.email.model.Message;
import group.avantis.email.responses.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@CustomExceptionHandler
public class EmailFacade {

    private final EmailServiceImpl emailService;
    private final MessageService messageService;

    @Autowired
    public EmailFacade(EmailServiceImpl emailService, MessageService messageService) {
        this.emailService = emailService;
        this.messageService = messageService;
    }

    public ResponseEntity<String> getMessageStatus(Integer messageId) {
        return ResponseEntity.ok().body("status: " + messageService.getMessageStatusById(messageId).get());
    }

    public ResponseEntity<MessageResponse> sendEmailWithAttachment(EmailRequest emailRequest) {
        CompletableFuture<Message> future = CompletableFuture.supplyAsync(() ->
                emailService.sendEmail(new MailRecord(emailRequest.getFrom(),
                        emailRequest.getTo(),
                        emailRequest.getSubject(),
                        emailRequest.getText(),
                        emailRequest.getAttachments())));
        return ResponseEntity.ok().body(new MessageResponse(future.join().getId()));
    }
}
