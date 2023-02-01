package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.DemandeReservationDTO;
import fr.inextenso.pratibook.dto.ValidationDemandeReservation;
import fr.inextenso.pratibook.exception.AlreadyReservedException;
import fr.inextenso.pratibook.exception.NotAvailableException;
import fr.inextenso.pratibook.service.ServiceReservation;
import fr.inextenso.pratibook.user.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
		return ResponseEntity.noContent().build();
	}

	@PreAuthorize("hasAuthority('Employe')")
	@GetMapping
	public ResponseEntity<List<DemandeReservationDTO>> getDemandesReservation() {
		logger.info("Récupération des demandes de réservation");
		return ResponseEntity.ok(this.serviceReservation.getListeDemandeReservation());
	}

	@PreAuthorize("hasAuthority('Employe')")
	@PostMapping
	public ResponseEntity<Void> accepterDemandeReservation(@RequestBody ValidationDemandeReservation demandeReservationDTO, Authentication authentication) {
		logger.info("Acceptation de la demande de réservation de l'oeuvre {} par {}", demandeReservationDTO.codeBarre(), demandeReservationDTO.idUser());
		try {
			int idUser = ((UserDetails) authentication.getPrincipal()).getUser().getId();
			this.serviceReservation.validerDemandeReservation(demandeReservationDTO, idUser);
		} catch (NotAvailableException e) {
			logger.warn("Erreur lors de l'acceptation de la demande de réservation de l'oeuvre {} par {}: {}", demandeReservationDTO.codeBarre(), demandeReservationDTO.idUser(), e.getMessage());
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
		}
		return ResponseEntity.noContent().build();
	}
}
