package group.avantus.mailApp.email.impl;


import group.avantus.mailApp.email.EmailService;
import group.avantus.mailApp.email.exception.EmailSendException;
import group.avantus.mailApp.message.MessageService;
import group.avantus.mailApp.message.file.FileService;
import group.avantus.mailApp.message.impl.FileServiceImpl;
import group.avantus.mailApp.message.impl.MessageServiceImpl;
import group.avantus.mailApp.message.model.FileEntity;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;

    private final MessageService messageService;

    private final FileService fileService;




    @Override
    @Transactional
    public void sendEmail(Message mail) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setFrom(mail.getFrom_email());
            messageHelper.setTo(mail.getTo_email());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mail.getText());

            List<FileEntity> attachments = fileService.getFiles(mail.getId());

            if (attachments != null && !attachments.isEmpty()) {
                for (FileEntity file : attachments) {
                    Resource resource = new ByteArrayResource(file.getData());
                    messageHelper.addAttachment(file.getFileName(), resource);
                }
            }

            mailSender.send(mimeMessage);

            messageService.updateStatus(mail.getId(), Status.SEND);
        } catch (Exception e) {
            messageService.updateStatus(mail.getId(), Status.ERROR);
            throw new EmailSendException(e.getMessage());
        }
    }

}
