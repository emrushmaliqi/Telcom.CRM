package exceptions;

public class SubscriptionException extends RuntimeException {
    public SubscriptionException() {
    }

    public SubscriptionException(String message) {
        super(message);
    }

    public SubscriptionException(Throwable cause) {
        super(cause);
    }
}
