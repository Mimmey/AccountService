package account.config.exceptions.notfoundexceptions;

public class EmailNotFoundException extends NotFoundException {

    private static final String message = "Email not found!";

    public EmailNotFoundException() {
        super(message);
    }
}
