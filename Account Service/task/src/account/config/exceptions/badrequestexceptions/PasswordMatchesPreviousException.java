package account.config.exceptions.badrequestexceptions;

public class PasswordMatchesPreviousException extends BadRequestException {

    private static final String message = "The passwords must be different!";

    public PasswordMatchesPreviousException() {
        super(message);
    }
}
