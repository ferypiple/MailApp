package group.avantis.email.api.v0;

import org.springframework.web.multipart.MultipartFile;

public record MailRecord(String from,
                         String to,
                         String subject,
                         String text,
                         MultipartFile[] attachments
) {

}
