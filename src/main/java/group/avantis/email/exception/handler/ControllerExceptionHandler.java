package group.avantis.email.exception.handler;

import group.avantis.email.exception.CustomExceptionHandler;
import group.avantis.email.exception.MessageNotSendException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(annotations = CustomExceptionHandler.class)
public class ControllerExceptionHandler {

    @ExceptionHandler(MessageNotSendException.class)
    public ResponseEntity<String> handleException(RuntimeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}

