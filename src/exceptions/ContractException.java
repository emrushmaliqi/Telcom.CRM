package exceptions;

public class ContractException extends RuntimeException{
    public ContractException() {
    }

    public ContractException(String message) {
        super(message);
    }

    public ContractException(Throwable cause) {
        super(cause);
    }
}
