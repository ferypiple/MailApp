package group.avantis.email.impl;

import group.avantis.email.EmailService;
import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailScheduler {

    private final EmailService emailService;
    private final MessageService messageService;
    @Autowired
    public EmailScheduler(MessageService messageService, EmailService emailService) {
        this.emailService = emailService;
        this.messageService = messageService;
    }


    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
    public void sendPendingEmails() {
        List<Message> pendingMessages = messageService.getMessages(Status.PENDING);
        for (Message message : pendingMessages) {
             emailService.sendEmail(message);
         }
    }
}