package group.avantus.mailApp.email.impl;


import group.avantus.mailApp.exception.CustomExceptionHandler;
import group.avantus.mailApp.email.exception.MessageNotSendException;
import group.avantus.mailApp.email.EmailService;
import group.avantus.mailApp.message.file.impl.usecase.query.GetFileQuery;
import group.avantus.mailApp.message.impl.MessageServiceImpl;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

@CustomExceptionHandler
@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;

    private final MessageServiceImpl messageServiceImpl;
    private final GetFileQuery getFileQuery;


    @Autowired
    public EmailServiceImpl(MessageServiceImpl messageServiceImpl, JavaMailSender mailSender, GetFileQuery getFileQuery) {
        this.messageServiceImpl = messageServiceImpl;
        this.mailSender = mailSender;
        this.getFileQuery = getFileQuery;
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


            List<FileEntity> attachments = getFileQuery.execute(mail.getId());

            if (attachments != null && attachments.size() > 0) {
               for (FileEntity file : attachments) {
                   Resource resource = new ByteArrayResource(file.getData());
                   messageHelper.addAttachment(file.getFileName(), resource);
                }
           }


            mailSender.send(mimeMessage);


            messageServiceImpl.updateStatus(mail.getId(), Status.SEND);
        } catch (Exception e) {
            messageServiceImpl.updateStatus(mail.getId(), Status.ERROR);
            throw new MessageNotSendException();
        }
    }

}
