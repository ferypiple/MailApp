package group.avantis.email.exceptions;

import java.time.Instant;

public class MessageNotSendException extends RuntimeException {

    public MessageNotSendException() {
        super("timestamp: " + Instant.now() + "\n message: Message not send!" );
    }
}
