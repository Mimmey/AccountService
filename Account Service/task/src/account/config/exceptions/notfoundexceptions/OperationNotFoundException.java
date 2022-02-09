package account.config.exceptions.notfoundexceptions;

public class OperationNotFoundException extends NotFoundException {

    private static final String message = "Operation not found!";

    public OperationNotFoundException() {
        super(message);
    }
}
