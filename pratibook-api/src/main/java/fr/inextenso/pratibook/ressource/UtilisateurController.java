package fr.inextenso.pratibook.ressource;

import fr.inextenso.pratibook.dto.UserPublicDTO;
import fr.inextenso.pratibook.service.ServiceUtilisateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurController {
	private final Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

	private final ServiceUtilisateur serviceUtilisateur;


	public UtilisateurController(ServiceUtilisateur serviceUtilisateur) {
		this.serviceUtilisateur = serviceUtilisateur;
	}

	@GetMapping
	@PreAuthorize("hasAuthority('Employe')")
	public ResponseEntity<List<UserPublicDTO>> findAllUser() {
		logger.info("getAllUsers");
		return ResponseEntity.ok(serviceUtilisateur.getAllUsers());
	}
}
