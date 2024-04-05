package group.avantis.email.impl;


import group.avantis.email.api.v0.MailRecord;
import group.avantis.email.exceptions.MessageNotSendException;
import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import group.avantis.email.EmailService;
import group.avantis.email.responses.MessageResponse;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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
    public Message sendEmail(MailRecord mailRecord) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        Message messageEntity = Message.builder()
                .from_email(mailRecord.from())
                .to_email(mailRecord.to())
                .status(Status.PENDING)
                .subject(mailRecord.subject())
                .text(mailRecord.text())
                .send_date(LocalDateTime.now())
                .build();
        messageService.saveMessage(messageEntity);

        try {


            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                    true);
            messageHelper.setFrom(mailRecord.from());

            messageHelper.setTo(mailRecord.to());

            messageHelper.setSubject(mailRecord.subject());

            messageHelper.setText(mailRecord.text());


            MultipartFile[] attachments = mailRecord.attachments();

            if (mailRecord.attachments() != null && attachments.length > 0) {
                for (MultipartFile file : attachments) {
                    Resource resource = new ByteArrayResource(file.getBytes());
                    messageHelper.addAttachment(file.getOriginalFilename(), resource);
                }
            }
        } catch (Exception e) {
            messageService.updateMessageStatus(messageEntity.getId(), Status.ERROR);
            new MessageNotSendException().toString();
        }

        mailSender.send(mimeMessage);

        messageService.updateMessageStatus(messageEntity.getId(), Status.SEND);


        return messageEntity;
    }

}
