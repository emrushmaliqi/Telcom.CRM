package exceptions;

public class ProductException extends RuntimeException{
    public ProductException() {
    }

    public ProductException(String message) {
        super(message);
    }

    public ProductException(Throwable cause) {
        super(cause);
    }
}
