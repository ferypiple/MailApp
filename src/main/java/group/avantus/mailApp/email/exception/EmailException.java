package group.avantus.mailApp.email.exception;

public abstract class EmailException extends RuntimeException {

  public EmailException(String message) {
    super(message);
  }
}
