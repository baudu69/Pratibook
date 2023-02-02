package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.EmprunterDTO;
import fr.inextenso.pratibook.model.Disponibilite;
import fr.inextenso.pratibook.model.InstanceOeuvre;
import fr.inextenso.pratibook.model.Location;
import fr.inextenso.pratibook.repository.InstanceOeuvreRepository;
import fr.inextenso.pratibook.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ServiceLocation {
	private final LocationRepository locationRepository;
	private final InstanceOeuvreRepository instanceOeuvreRepository;

	public ServiceLocation(LocationRepository locationRepository,
	                       InstanceOeuvreRepository instanceOeuvreRepository) {
		this.locationRepository = locationRepository;
		this.instanceOeuvreRepository = instanceOeuvreRepository;
	}

	@Transactional
	public void emprunter(EmprunterDTO emprunterDTO) {
		//Check si le code barre existe
		InstanceOeuvre instanceOeuvre = instanceOeuvreRepository.findById(emprunterDTO.codeBarre())
				.orElseThrow(() -> new RuntimeException("Code barre inconnu"));
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
		instanceOeuvre.setEtatDisponibilite(Disponibilite.EMPRUNTE);
		instanceOeuvreRepository.save(instanceOeuvre);
	}
}
