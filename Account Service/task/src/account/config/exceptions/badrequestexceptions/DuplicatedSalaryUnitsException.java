package account.config.exceptions.badrequestexceptions;

public class DuplicatedSalaryUnitsException extends BadRequestException {

    private static final String message = "Duplicated salary units!";

    public DuplicatedSalaryUnitsException() {
        super(message);
    }
}