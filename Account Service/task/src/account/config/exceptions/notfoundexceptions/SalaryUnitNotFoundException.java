package account.config.exceptions.notfoundexceptions;

public class SalaryUnitNotFoundException extends NotFoundException {

    private static final String message = "Salary unit not found!";

    public SalaryUnitNotFoundException() {
        super(message);
    }
}