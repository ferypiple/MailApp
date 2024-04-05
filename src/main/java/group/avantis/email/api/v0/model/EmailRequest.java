package group.avantis.email.api.v0.model;


import lombok.Builder;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EmailRequest {
    private String from;
    private String to;
    private String subject;
    private String text;
    private MultipartFile[] attachments;

}
