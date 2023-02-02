package fr.inextenso.pratibook.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

public class ReferencedEntityException extends ResponseStatusException {

    public ReferencedEntityException() {
        super(CONFLICT);
    }

    public ReferencedEntityException(String reason) {
        super(CONFLICT, reason);
    }

    public ReferencedEntityException(String reason, Throwable cause) {
        super(CONFLICT, reason, cause);
    }

    protected ReferencedEntityException(String reason, Throwable cause, String messageDetailCode, Object[] messageDetailArguments) {
        super(CONFLICT, reason, cause, messageDetailCode, messageDetailArguments);
    }

}
