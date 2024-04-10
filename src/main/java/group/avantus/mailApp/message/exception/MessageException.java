package group.avantus.mailApp.message.exception;

public abstract class MessageException extends RuntimeException {
    public MessageException(String message) {
        super(message);
    }
}
