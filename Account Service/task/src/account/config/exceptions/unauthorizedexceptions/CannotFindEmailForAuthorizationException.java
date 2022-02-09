package account.config.exceptions.unauthorizedexceptions;

import account.config.exceptions.notfoundexceptions.NotFoundException;

public class CannotFindEmailForAuthorizationException extends UnauthorizedException {

    private static final String message = "Not found email: %s";

    public CannotFindEmailForAuthorizationException(String email) {
        super(String.format(message, email));
    }
}