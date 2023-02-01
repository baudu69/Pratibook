package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.EmprunterDTO;
import fr.inextenso.pratibook.model.Location;
import fr.inextenso.pratibook.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServiceLocation {
	private final LocationRepository locationRepository;

	public ServiceLocation(LocationRepository locationRepository) {
		this.locationRepository = locationRepository;
	}

	@Transactional
	public void emprunter(EmprunterDTO emprunterDTO) {
		//Check si une instance existe déjà (réservation)
		Optional<Location> loc = locationRepository.findByCodeBarreAndIdUtilisateurAndDateRenduReel(emprunterDTO.codeBarre(), emprunterDTO.userID(), null);
		loc.ifPresent(location -> {
			if (location.getDateLocation() != null && location.getDateRenduReel() == null)
				throw new RuntimeException("Livre déjà emprunté");
		});
		Location location = loc.orElseGet(Location::new);
		location.setDateLocation(LocalDateTime.now());
		location.setDateRenduTheorique(LocalDateTime.now().plusWeeks(2).toLocalDate());
		location.setEtatPhysiqueLocation(emprunterDTO.etat());
		location.setCodeBarre(emprunterDTO.codeBarre());
		location.setIdUtilisateur(emprunterDTO.userID());
		locationRepository.save(location);
		//TODO : Etat instance de l'oeuvre à 0
	}
}
