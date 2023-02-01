package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.exception.AlreadyReservedException;
import fr.inextenso.pratibook.exception.NotAvailableException;
import fr.inextenso.pratibook.service.ServiceReservation;
import fr.inextenso.pratibook.user.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
	private final Logger logger = LoggerFactory.getLogger(ReservationController.class);
	private final ServiceReservation serviceReservation;

	public ReservationController(ServiceReservation serviceReservation) {
		this.serviceReservation = serviceReservation;
	}

	@GetMapping("/demande/{idOeuvre}")
	public ResponseEntity<Void> demandeReservation(@PathVariable int idOeuvre, Authentication authentication) {
		logger.info("Demande de réservation de l'oeuvre {} par {}", idOeuvre, authentication.getName());
		int idUser = ((UserDetails) authentication.getPrincipal()).getUser().getId();
		try {
			this.serviceReservation.demandeReservation(idOeuvre, idUser);
		} catch (AlreadyReservedException | NotAvailableException e) {
			logger.warn("Erreur lors de la demande de réservation de l'oeuvre {} par {}: {}", idOeuvre, authentication.getName(), e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		return ResponseEntity.ok().build();
	}
}
