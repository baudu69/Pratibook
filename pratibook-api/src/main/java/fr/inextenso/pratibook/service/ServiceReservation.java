package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.exception.AlreadyReservedException;
import fr.inextenso.pratibook.exception.NotAvailableException;
import fr.inextenso.pratibook.model.DemandeReservation;
import fr.inextenso.pratibook.model.Oeuvre;
import fr.inextenso.pratibook.repository.DemandeReservationRepository;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ServiceReservation {
	private final DemandeReservationRepository demandeReservationRepository;
	private final OeuvreRepository oeuvreRepository;

	public ServiceReservation(DemandeReservationRepository demandeReservationRepository, OeuvreRepository oeuvreRepository) {
		this.demandeReservationRepository = demandeReservationRepository;
		this.oeuvreRepository = oeuvreRepository;
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
}
