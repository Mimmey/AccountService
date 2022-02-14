package account.config.exceptions.unauthorizedexceptions;

import account.config.exceptions.ExceptionThrower;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnauthorizedExceptionThrower extends ExceptionThrower {
    private static HttpStatus status = HttpStatus.UNAUTHORIZED;

    public static void throwCannotFindEmailForAuthorizationException(String email) {
        throw new ResponseStatusException(status, String.format("Not found email: %s", email));
    }
}
