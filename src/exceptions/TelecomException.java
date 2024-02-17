package exceptions;

public class TelecomException extends RuntimeException {
    public TelecomException() {
    }

    public TelecomException(String message) {
        super(message);
    }

    public TelecomException(Throwable cause) {
        super(cause);
    }
}
