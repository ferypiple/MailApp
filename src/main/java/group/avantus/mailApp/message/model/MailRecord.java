package group.avantus.mailApp.message.model;

import org.springframework.web.multipart.MultipartFile;

public record MailRecord(
    String from,
    String to,
    String subject,
    String text,
    MultipartFile[] attachments
) {

}
