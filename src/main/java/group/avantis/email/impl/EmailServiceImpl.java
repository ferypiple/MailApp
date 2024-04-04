package group.avantis.email.impl;


import group.avantis.email.exceptions.MessageNotSendException;
import group.avantis.email.model.Message;
import group.avantis.email.model.Status;
import group.avantis.email.services.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
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
    public String sendEmailWithAttachment(String fromAddress, String toAddress, String subject, String message, MultipartFile[] attachments) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        Message messageEntity = Message.builder()
                .from_email(fromAddress)
                .to_email(toAddress)
                .status(Status.PENDING)
                .subject(subject)
                .send_date(LocalDateTime.now())
                .build();
        messageService.saveMessage(messageEntity);

        try {


            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                    true);
            messageHelper.setFrom(fromAddress);

            messageHelper.setTo(toAddress);

            messageHelper.setSubject(subject);

            messageHelper.setText(message);

            if (attachments != null && attachments.length > 0) {
                for (MultipartFile file : attachments) {
                    Resource resource = new ByteArrayResource(file.getBytes());
                    messageHelper.addAttachment(file.getOriginalFilename(), resource);
                }
            }
        } catch (Exception e) {
            messageService.updateMessageStatus(messageEntity.getId(), Status.ERROR);
            e.printStackTrace();
            return "timestamp:" + Instant.now() + "message:" + new MessageNotSendException();
        }

        mailSender.send(mimeMessage);

        messageService.updateMessageStatus(messageEntity.getId(), Status.SEND);


        return "id: " + messageEntity.getId().toString();
    }

}
