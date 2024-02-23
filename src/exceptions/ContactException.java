package exceptions;

public class ContactException extends RuntimeException{

    public ContactException() {
    }

    public ContactException(String message) {
        super(message);
    }

    public ContactException(Throwable cause) {
        super(cause);
    }
}
