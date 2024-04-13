package group.avantus.mailApp.message.exception;

import group.avantus.mailApp.message.exception.MessageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MessageNotFoundException extends MessageException {
    public MessageNotFoundException(long id) {
        super("Сообщение с идентификатором " + id + " не найдено");
    }
}