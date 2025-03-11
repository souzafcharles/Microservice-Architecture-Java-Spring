package souza.souza.charles.transactionservice.exceptions;

public class InvalidUserAccountException extends RuntimeException {
    public InvalidUserAccountException(String message) {
        super(message);
    }
}