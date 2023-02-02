package fr.inextenso.pratibook.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class NotFoundException extends ResponseStatusException {

    public NotFoundException() {
        super(NOT_FOUND);
    }

    public NotFoundException(String reason) {
        super(NOT_FOUND, reason);
    }

    public NotFoundException(String reason, Throwable cause) {
        super(NOT_FOUND, reason, cause);
    }

    protected NotFoundException(String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(NOT_FOUND, reason, cause, messageDetailCode, messageDetailArguments);
    }

}
