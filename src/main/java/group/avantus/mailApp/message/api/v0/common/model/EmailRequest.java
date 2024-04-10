package group.avantus.mailApp.message.api.v0.common.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data

public class EmailRequest {


    private String from;

    private String to;

    private String subject;


    private String text;

    private MultipartFile[] attachments;

}
