package account.config.exceptions.notfoundexceptions;

import account.config.exceptions.ExceptionThrower;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundExceptionThrower extends ExceptionThrower {
    private static HttpStatus status = HttpStatus.NOT_FOUND;

    public static void throwEmailNotFoundException() {
        throw new ResponseStatusException(status, "Email not found!");
    }

    public static void throwOperationNotFoundException() {
        throw new ResponseStatusException(status, "Operation not found!");
    }

    public static void throwRoleNotFoundExceptionError() {
        throw new ResponseStatusException(status, "Role not found!");
    }

    public static void throwSalaryUnitNotFoundException() {
        throw new ResponseStatusException(status, "Salary unit not found!");
    }

    public static void throwUserNotFoundException() {
        throw new ResponseStatusException(status, "User not found!");
    }
}
