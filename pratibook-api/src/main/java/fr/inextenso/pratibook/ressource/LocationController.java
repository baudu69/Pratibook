package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.EmprunterDTO;
import fr.inextenso.pratibook.dto.RenduDTO;
import fr.inextenso.pratibook.service.ServiceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/rendre")
	public ResponseEntity<Void> rendreOeuvre(@RequestBody RenduDTO renduDTO) {
		logger.info("Rendu de l'oeuvre {}", renduDTO);
		try {
			this.serviceLocation.rendu(renduDTO);
		} catch (Exception e) {
			logger.error("Erreur lors du rendu de l'oeuvre {}", renduDTO, e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		return ResponseEntity.noContent().build();
	}
}
