package group.avantus.mailApp.email.exception;

import java.time.Instant;

public class EmailSendException extends EmailException {

  public EmailSendException(String message) {

    super("timestamp: " + Instant.now() + "\n message: Message not send!" + "Error:" + message);
  }
}
