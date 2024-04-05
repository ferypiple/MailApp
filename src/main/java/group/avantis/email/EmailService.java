package group.avantis.email;

import group.avantis.email.model.Message;
import group.avantis.email.responses.MessageResponse;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {
    MessageResponse sendEmail(String fromAddress, String toAddress, String subject, String message, MultipartFile[] attachments) throws MessagingException, IOException;
}
