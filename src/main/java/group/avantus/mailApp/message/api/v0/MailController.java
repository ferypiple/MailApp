package group.avantus.mailApp.message.api.v0;

import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.api.v0.common.model.EmailRequest;
import group.avantus.mailApp.exception.CustomExceptionHandler;
import group.avantus.mailApp.message.impl.MessageServiceImpl;
import group.avantus.mailApp.message.model.MailRecord;
import group.avantus.mailApp.message.api.v0.common.model.MessageResponse;
import group.avantus.mailApp.message.api.v0.common.model.StatusResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CustomExceptionHandler
@RestController
@RequestMapping("/mailapp/api/v0/mail")
public class MailController {

  private final MessageService messageService;

  @Autowired
  public MailController(MessageServiceImpl messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/{id}/status")
  public StatusResponse getMessageStatus(@PathVariable("id") Long messageId) {
    return new StatusResponse(messageService.getMessage(messageId).get().getStatus());
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
