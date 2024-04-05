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

    private final EmailFacade emailFacade;

    @Autowired
    public MailController(EmailFacade emailFacade) {
        this.emailFacade = emailFacade;
    }

    @GetMapping("/{id}/status")
    public ResponseEntity<String> getMessageStatus(@PathVariable("id") Integer messageId) {
        return emailFacade.getMessageStatus(messageId);
    }

    @PostMapping
    public ResponseEntity<MessageResponse> sendEmailWithAttachment(@ModelAttribute EmailRequest emailRequest) {
        return emailFacade.sendEmailWithAttachment( emailRequest);
    }
}
