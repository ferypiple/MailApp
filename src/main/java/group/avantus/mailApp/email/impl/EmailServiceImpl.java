package group.avantus.mailApp.email.impl;


import group.avantus.mailApp.exception.CustomExceptionHandler;
import group.avantus.mailApp.exception.MessageNotSendException;
import group.avantus.mailApp.EmailService;
import group.avantus.mailApp.message.impl.MessageServiceImpl;
import group.avantus.mailApp.message.model.Message;
import group.avantus.mailApp.message.model.Status;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@CustomExceptionHandler
@Service
public class EmailServiceImpl implements EmailService {


    private final JavaMailSender mailSender;

    private final MessageServiceImpl messageServiceImpl;


    @Autowired
    public EmailServiceImpl(MessageServiceImpl messageServiceImpl, JavaMailSender mailSender) {
        this.messageServiceImpl = messageServiceImpl;
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


            messageServiceImpl.updateMessageStatus(mail.getId(), Status.SEND);
        } catch (Exception e) {
            messageServiceImpl.updateMessageStatus(mail.getId(), Status.ERROR);
            throw new MessageNotSendException();
        }
    }

}
