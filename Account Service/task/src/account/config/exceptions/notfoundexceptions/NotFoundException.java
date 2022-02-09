package account.config.exceptions.notfoundexceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public abstract class NotFoundException extends ResponseStatusException {

    private final static HttpStatus status = HttpStatus.NOT_FOUND;

    public NotFoundException(String reason) {
        super(status, reason);
    }
}
