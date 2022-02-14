package account.config.exceptions.internalservererrorexceptions;

import account.config.exceptions.ExceptionThrower;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerErrorExceptionThrower extends ExceptionThrower {
    private static HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public static void throwUpdatingSalaryUnitException() {
        throw new ResponseStatusException(status, "Error while updating!");
    }
}
