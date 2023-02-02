package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.DemandeReservationDTO;
import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.dto.ValidationDemandeReservation;
import fr.inextenso.pratibook.exception.AlreadyReservedException;
import fr.inextenso.pratibook.exception.NotAvailableException;
import fr.inextenso.pratibook.model.*;
import fr.inextenso.pratibook.repository.DemandeReservationRepository;
import fr.inextenso.pratibook.repository.InstanceOeuvreRepository;
import fr.inextenso.pratibook.repository.LocationRepository;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceReservation {
	private final DemandeReservationRepository demandeReservationRepository;
	private final OeuvreRepository oeuvreRepository;
	private final ServiceOeuvre serviceOeuvre;
	private final LocationRepository locationRepository;
	private final InstanceOeuvreRepository instanceOeuvreRepository;

	public ServiceReservation(DemandeReservationRepository demandeReservationRepository, OeuvreRepository oeuvreRepository, ServiceOeuvre serviceOeuvre, LocationRepository locationRepository, InstanceOeuvreRepository instanceOeuvreRepository) {
		this.demandeReservationRepository = demandeReservationRepository;
		this.oeuvreRepository = oeuvreRepository;
		this.serviceOeuvre = serviceOeuvre;
		this.locationRepository = locationRepository;
		this.instanceOeuvreRepository = instanceOeuvreRepository;
	}

	/**
	 * Demande de réservation d'une œuvre
	 *
	 * @param idOeuvre id de l'œuvre
	 * @param idUser   id de l'user
	 */
	public void demandeReservation(int idOeuvre, int idUser) {
		if (this.demandeReservationRepository.existsById_IdUserAndId_IdOeuvre(idUser, idOeuvre)) {
			throw new AlreadyReservedException("L'oeuvre a déjà été réservée par cet utilisateur");
		}
		final Oeuvre oeuvre = this.oeuvreRepository.findById(idOeuvre).orElseThrow(() -> new IllegalArgumentException("L'oeuvre n'existe pas"));
		if (ServiceOeuvre.getNbInstanceDisponibles(oeuvre) == 0) {
			throw new NotAvailableException("L'oeuvre n'est pas disponible");
		}
		final DemandeReservation demandeReservation = new DemandeReservation(idOeuvre, idUser, LocalDateTime.now());
		this.demandeReservationRepository.save(demandeReservation);
	}

	public List<DemandeReservationDTO> getListeDemandeReservation() {
		return this.demandeReservationRepository.findAll()
				.stream()
				.map(demande -> {
					OeuvreDTO oeuvreDTO = this.serviceOeuvre.findById(demande.getId().getIdOeuvre());
					return new DemandeReservationDTO(oeuvreDTO, demande.getId().getIdUser(), demande.getDateDemande());
				})
				.toList();
	}

	@Transactional(rollbackOn = Exception.class)
	public void validerDemandeReservation(ValidationDemandeReservation demandeReservationDTO, int idEmploye) {
		InstanceOeuvre instanceOeuvre = this.instanceOeuvreRepository.findById(demandeReservationDTO.codeBarre()).orElseThrow(() -> new IllegalArgumentException("L'instance d'oeuvre n'existe pas"));
		if (this.demandeReservationRepository.deleteById_IdUserAndId_IdOeuvre(demandeReservationDTO.idUser(), instanceOeuvre.getOeuvre().getId()) != 1) {
			throw new IllegalArgumentException("La demande de réservation n'existe pas");
		}
		instanceOeuvre.setEtatDisponibilite(Disponibilite.EMPRUNTE);
		this.instanceOeuvreRepository.save(instanceOeuvre);
		Location location = new Location();
		location.setCodeBarre(demandeReservationDTO.codeBarre());
		location.setIdEmploye(idEmploye);
		location.setIdUtilisateur(demandeReservationDTO.idUser());
		location.setDateReservation(LocalDateTime.now());
		this.locationRepository.save(location);
	}
}
