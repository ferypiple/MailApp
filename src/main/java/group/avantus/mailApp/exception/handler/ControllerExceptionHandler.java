package group.avantus.mailApp.exception.handler;

import group.avantus.mailApp.exception.CustomExceptionHandler;
import group.avantus.mailApp.email.exception.EmailSendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = CustomExceptionHandler.class)
public class ControllerExceptionHandler {

  @ExceptionHandler(EmailSendException.class)
  public ResponseEntity<String> handleException(RuntimeException e) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> notValid() {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверные переменные");
  }
}

