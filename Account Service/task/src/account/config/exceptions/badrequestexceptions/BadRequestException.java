package account.config.exceptions.badrequestexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class BadRequestException extends ResponseStatusException {

    private final static HttpStatus status = HttpStatus.BAD_REQUEST;

    public BadRequestException(String reason) {
        super(status, reason);
    }

    public BadRequestException(String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
