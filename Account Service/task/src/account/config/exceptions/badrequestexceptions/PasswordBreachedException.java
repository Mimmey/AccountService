package account.config.exceptions.badrequestexceptions;

public class PasswordBreachedException extends BadRequestException {

    private static final String message = "The password is in the hacker's database!";

    public PasswordBreachedException() {
        super(message);
    }
}
