package account.config.exceptions.badrequestexceptions;

public class PasswordTooShortException extends BadRequestException {

    private static final String message = "Password length must be %d chars minimum!";

    public PasswordTooShortException(int minSize) {
        super(String.format(message, minSize));
    }
}
