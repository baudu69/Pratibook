package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.EmprunterDTO;
import fr.inextenso.pratibook.service.ServiceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/location")
public class LocationController {
	private final Logger logger = LoggerFactory.getLogger(LocationController.class);
	private final ServiceLocation serviceLocation;

	public LocationController(ServiceLocation serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	@PreAuthorize("hasAuthority('Employe')")
	@PostMapping("/emprunter")
	public ResponseEntity<Void> emprunterOeuvre(@RequestBody EmprunterDTO emprunterDTO) {
		logger.info("Emprunt de l'oeuvre {} par {}", emprunterDTO, emprunterDTO.userID());
		try {
			this.serviceLocation.emprunter(emprunterDTO);
		} catch (Exception e) {
			logger.error("Erreur lors de l'emprunt de l'oeuvre {} par {}", emprunterDTO, emprunterDTO.userID(), e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return ResponseEntity.noContent().build();
	}
}
