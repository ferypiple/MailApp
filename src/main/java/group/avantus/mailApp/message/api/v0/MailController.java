package group.avantus.mailApp.message.api.v0;


import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.api.v0.common.model.EmailRequest;
import group.avantus.mailApp.message.api.v0.common.model.MessageResponse;
import group.avantus.mailApp.message.api.v0.common.model.StatusResponse;
import group.avantus.mailApp.message.model.MailRecord;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mailapp/api/v0/mail")
public class MailController {

    private final MessageService messageService;

    @GetMapping("/{id}/status")
    public StatusResponse getMessageStatus(@PathVariable("id") Long messageId) {
        return new StatusResponse(messageService.getMessage(messageId));
    }

    @PostMapping
    public MessageResponse addMessage(@ModelAttribute @Valid EmailRequest emailRequest) {
        MailRecord mailRecord = new MailRecord(
                emailRequest.getFrom(),
                emailRequest.getTo(),
                emailRequest.getSubject(),
                emailRequest.getText(),
                emailRequest.getAttachments());
        return new MessageResponse(messageService.saveMessage(mailRecord));
    }
}
