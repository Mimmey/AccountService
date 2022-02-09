package account.config.exceptions.badrequestexceptions;

public class UserAlreadyExistException extends BadRequestException {

    private static final String message = "User already exists!";

    public UserAlreadyExistException() {
        super(message);
    }
}
