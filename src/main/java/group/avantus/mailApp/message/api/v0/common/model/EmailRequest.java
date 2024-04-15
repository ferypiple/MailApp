package group.avantus.mailApp.message.api.v0.common.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

@Data
@Validated
public class EmailRequest {

  @NotBlank(message = "Поле 'from' не может быть пустым")
  @Email(message = "Некорректный адрес 'from'")
  private String from;

  @NotBlank(message = "Поле 'to' не может быть пустым")
  @Email(message = "Некорректный адрес 'to'")
  private String to;

  @NotBlank(message = "Поле 'subject' не может быть пустым")
  @Size(max = 255, message = "Длина 'subject' не может превышать 255 символов")
  private String subject;

  @NotBlank(message = "Поле 'text' не может быть пустым")
  private String text;

  private MultipartFile[] attachments;


}
