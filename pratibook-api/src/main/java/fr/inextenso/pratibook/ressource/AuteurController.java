package fr.inextenso.pratibook.ressource;

import java.util.List;

import fr.inextenso.pratibook.dto.AuteurDTO;
import fr.inextenso.pratibook.dto.AuteurWithOeuvresDTO;
import fr.inextenso.pratibook.service.ServiceAuteur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auteur")
public class AuteurController {

	private final ServiceAuteur serviceAuteur;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public AuteurController(ServiceAuteur serviceAuteur) {
		this.serviceAuteur = serviceAuteur;
	}

	@GetMapping
	public ResponseEntity<List<AuteurDTO>> getAuteurs() {
		logger.info("REST GET /api/auteur");
		return ResponseEntity.ok(this.serviceAuteur.getAllAuteurs());
	}

	@GetMapping("/{id}")
	public ResponseEntity<AuteurWithOeuvresDTO> getAuteur(@PathVariable Integer id) {
		logger.info("REST GET /api/auteur/{}", id);
		return ResponseEntity.ok(this.serviceAuteur.getAuteurWithOeuvres(id));
	}

}
