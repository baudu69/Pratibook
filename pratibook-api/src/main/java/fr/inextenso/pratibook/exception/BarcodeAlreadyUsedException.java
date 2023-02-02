package fr.inextenso.pratibook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BarcodeAlreadyUsedException extends ResponseStatusException {

    public BarcodeAlreadyUsedException() {
        super(HttpStatus.BAD_REQUEST);
    }

}
