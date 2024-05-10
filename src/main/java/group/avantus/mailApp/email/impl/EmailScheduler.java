package group.avantus.mailApp.email.impl;

import group.avantus.mailApp.email.EmailService;
import group.avantus.mailApp.message.impl.usecase.query.FindMessageByStatusQuery;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Component
public class EmailScheduler {

    private final EmailService emailService;

    private final FindMessageByStatusQuery findMessageByStatusQuery;


    @Transactional
    @Scheduled(fixedRateString = "${bot.recountNewArticleFixedRate}", initialDelay = 5000)
    public void sendPendingEmails() {
        List<Message> pendingMessages = findMessageByStatusQuery.execute(Status.PENDING);
        for (Message message : pendingMessages) {
            emailService.sendEmail(message);
        }
    }
}