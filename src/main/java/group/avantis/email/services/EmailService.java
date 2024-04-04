package group.avantis.email.services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;


    public void sendEmailWithAttachment(String fromAddress, String toAddress, String subject, String message, MultipartFile[] attachments) throws MessagingException, IOException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,
                true,
                StandardCharsets.UTF_8.name());
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

      mailSender.send(mimeMessage);

    }

}
