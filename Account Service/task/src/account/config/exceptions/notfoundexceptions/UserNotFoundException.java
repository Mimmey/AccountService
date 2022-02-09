package account.config.exceptions.notfoundexceptions;

public class UserNotFoundException extends NotFoundException {

    private static final String message = "User not found!";

    public UserNotFoundException() {
        super(message);
    }
}
