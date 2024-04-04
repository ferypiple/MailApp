package group.avantis.email.services;

import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {
    String sendEmailWithAttachment(String fromAddress, String toAddress, String subject, String message, MultipartFile[] attachments) throws MessagingException, IOException;
}
