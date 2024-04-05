package group.avantis.email;

import group.avantis.email.api.v0.MailRecord;
import group.avantis.email.api.v0.model.EmailRequest;
import group.avantis.email.model.Message;
import group.avantis.email.responses.MessageResponse;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface EmailService {
    Message sendEmail(MailRecord mailRecord) throws MessagingException, IOException;
}
