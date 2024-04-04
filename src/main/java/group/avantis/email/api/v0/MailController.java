package group.avantis.email.api.v0;

import group.avantis.email.api.v0.model.EmailRequest;
import group.avantis.email.exceptions.MessageNotSendException;
import group.avantis.email.impl.EmailServiceImpl;
import group.avantis.email.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/mailer/api/v0/mail")
public class MailController {

    private final EmailServiceImpl emailServiceImpl;
    private final MessageService messageService;
    @Autowired
    public MailController(EmailServiceImpl emailServiceImpl,MessageService messageService){
        this.emailServiceImpl = emailServiceImpl;
        this.messageService = messageService;
    }


    @GetMapping("/{id}/status")
    public ResponseEntity<String> getMessageStatus(@PathVariable("id") Integer  messageId) {
        try {
            return ResponseEntity.ok().body("status: " + messageService.getMessageStatusById(messageId).get());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Id not found");
        }
    }


    @PostMapping
    public ResponseEntity<String> sendEmailWithAttachment(
            @ModelAttribute EmailRequest emailRequest) {
        try {
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() ->
                    emailServiceImpl.sendEmailWithAttachment(emailRequest.getFrom(),
                            emailRequest.getTo(),
                            emailRequest.getSubject(),
                            emailRequest.getText(),
                            emailRequest.getAttachments()));
            return ResponseEntity.ok().body(future.join());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
