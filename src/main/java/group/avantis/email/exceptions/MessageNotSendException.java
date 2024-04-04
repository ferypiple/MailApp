package group.avantis.email.exceptions;

import java.time.Instant;

public class MessageNotSendException extends RuntimeException {
    private String message;

    public MessageNotSendException() {
        message = "timestamp: " + Instant.now() + "\n message: Message not send!";
    }
    @Override
    public String toString() {
        return message;
    }
}
