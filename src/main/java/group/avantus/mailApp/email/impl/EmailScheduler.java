package group.avantus.mailApp.email.impl;

import group.avantus.mailApp.email.EmailService;
import group.avantus.mailApp.message.impl.usecase.query.FindMessageByStatusQuery;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailScheduler {

    private final EmailService emailService;

    private final FindMessageByStatusQuery findMessageByStatusQuery;
    @Autowired
    public EmailScheduler(EmailService emailService, FindMessageByStatusQuery findMessageByStatusQuery) {
        this.emailService = emailService;
        this.findMessageByStatusQuery = findMessageByStatusQuery;
    }


    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}")
    public void sendPendingEmails() {
        List<Message> pendingMessages = findMessageByStatusQuery.execute(Status.PENDING);
        for (Message message : pendingMessages) {
             emailService.sendEmail(message);
         }
    }
}