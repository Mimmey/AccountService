package account.config.exceptions.unauthorizedexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class UnauthorizedException extends ResponseStatusException {

    private final static HttpStatus status = HttpStatus.NOT_FOUND;

    public UnauthorizedException(String reason) {
        super(status, reason);
    }
}