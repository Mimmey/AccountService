package account.config.exceptions.internalservererrorexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerErrorException extends ResponseStatusException {

    private final static HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    public InternalServerErrorException(String reason) {
        super(status, reason);
    }

    public InternalServerErrorException(String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
