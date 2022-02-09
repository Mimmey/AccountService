package account.config.exceptions.internalservererrorexceptions;

import account.config.exceptions.notfoundexceptions.NotFoundException;

public class UpdatingSalaryUnitException extends NotFoundException {

    private static final String message = "Error while updating!";

    public UpdatingSalaryUnitException() {
        super(message);
    }
}
