package group.avantus.mailApp.email.impl;

import group.avantus.mailApp.EmailService;
import group.avantus.mailApp.impl.usecase.FindAllQuery;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailScheduler {

    private final EmailService emailService;

    private final FindAllQuery findAllQuery;
    @Autowired
    public EmailScheduler(EmailService emailService, FindAllQuery findAllQuery) {
        this.emailService = emailService;
        this.findAllQuery = findAllQuery;
    }


    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
    public void sendPendingEmails() {
        List<Message> pendingMessages = findAllQuery.execute(Status.PENDING);
        for (Message message : pendingMessages) {
             emailService.sendEmail(message);
         }
    }
}