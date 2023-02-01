package fr.inextenso.pratibook.exception;

public class AlreadyReservedException extends RuntimeException {
	public AlreadyReservedException(String mess) {
		super(mess);
	}
}
