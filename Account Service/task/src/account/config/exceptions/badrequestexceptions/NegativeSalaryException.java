package account.config.exceptions.badrequestexceptions;

public class NegativeSalaryException extends BadRequestException {

    private static final String message = "Salary must be non-negative!";

    public NegativeSalaryException() {
        super(message);
    }
}
