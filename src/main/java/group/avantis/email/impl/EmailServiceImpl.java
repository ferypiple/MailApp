package group.avantis.email.impl;


import group.avantis.email.api.v0.MailRecord;
import group.avantis.email.exception.CustomExceptionHandler;
import group.avantis.email.exception.MessageNotSendException;
import group.avantis.email.EmailService;
import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@CustomExceptionHandler
@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;

    private final MessageService messageService;


    @Autowired
    public EmailServiceImpl(MessageService messageService, JavaMailSender mailSender) {
        this.messageService = messageService;
        this.mailSender = mailSender;
    }

    @Override
    public void sendEmail(Message mail) {



        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(mail.getFrom_email());
            messageHelper.setTo(mail.getTo_email());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());


//            MultipartFile[] attachments = mailRecord.attachments();
//
//            if (attachments != null && attachments.length > 0) {
//                for (MultipartFile file : attachments) {
//                    Resource resource = new ByteArrayResource(file.getBytes());
//                    messageHelper.addAttachment(file.getOriginalFilename(), resource);
//                }
//            }


            mailSender.send(mimeMessage);


            messageService.updateMessageStatus(mail.getId(), Status.SEND);
        } catch (Exception e) {
            messageService.updateMessageStatus(mail.getId(), Status.ERROR);
            throw new MessageNotSendException();
        }
    }

}
