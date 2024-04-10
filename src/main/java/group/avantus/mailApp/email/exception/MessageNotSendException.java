package group.avantus.mailApp.email.exception;

import java.time.Instant;

public class MessageNotSendException extends RuntimeException {
    private String message;

    public MessageNotSendException() {

       super("timestamp: " + Instant.now() + "\n message: Message not send!");
        message = "timestamp: " + Instant.now() + "\n message: Message not send!";
    }
    @Override
    public String toString() {
        return message;
    }
}
