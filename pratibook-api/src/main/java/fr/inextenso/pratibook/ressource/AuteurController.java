package fr.inextenso.pratibook.ressource;

import java.util.List;

import fr.inextenso.pratibook.dto.AuteurDTO;
import fr.inextenso.pratibook.service.ServiceAuteur;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auteur")
public class AuteurController {

	private final ServiceAuteur serviceAuteur;

	public AuteurController(ServiceAuteur serviceAuteur) {
		this.serviceAuteur = serviceAuteur;
	}

	@GetMapping
	public ResponseEntity<List<AuteurDTO>> getAuteurs() {
		return ResponseEntity.ok(this.serviceAuteur.getAllAuteurs());
	}

}
