package account.config.exceptions.badrequestexceptions;

public class RemovingOfAdministratorException extends BadRequestException {

    private static final String message = "Can't remove ADMINISTRATOR role!";

    public RemovingOfAdministratorException() {
        super(message);
    }
}
