package account.config.exceptions.badrequestexceptions;

import account.config.exceptions.ExceptionThrower;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestExceptionThrower extends ExceptionThrower {
    private static HttpStatus status = HttpStatus.BAD_REQUEST;

    public static void throwDuplicatedSalaryUnitsException() {
        throw new ResponseStatusException(status, "Duplicated salary units!");
    }

    public static void throwNegativeSalaryException() {
        throw new ResponseStatusException(status, "Salary must be non-negative!");
    }

    public static void throwPasswordBreachedException() {
        throw new ResponseStatusException(status, "The password is in the hacker's database!");
    }

    public static void throwPasswordMatchesPreviousException() {
        throw new ResponseStatusException(status, "The passwords must be different!");
    }

    public static void throwPasswordTooShortException(int size) {
        throw new ResponseStatusException(status, String.format("Password length must be %d chars minimum!", size));
    }

    public static void throwRemovingAdministratorRoleException() {
        throw new ResponseStatusException(status, "Can't remove ADMINISTRATOR role!");
    }

    public static void throwRemovingOnlyExistingRoleException() {
        throw new ResponseStatusException(status, "The user must have at least one role!");
    }

    public static void throwUserDoesntHaveRoleException() {
        throw new ResponseStatusException(status, "The user does not have a role!");
    }

    public static void throwUserExistsException() {
        throw new ResponseStatusException(status, "User exist!");
    }

    public static void throwAdministratorAccountRolesConflictException() {
        throw new ResponseStatusException(status, "The user cannot combine administrative and business roles!");
    }
}
