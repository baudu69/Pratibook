package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.DemandeReservation;
import fr.inextenso.pratibook.model.DemandeReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemandeReservationRepository extends JpaRepository<DemandeReservation, DemandeReservationId> {
	long deleteById_IdUserAndId_IdOeuvre(Integer idUser, Integer idOeuvre);

	boolean existsById_IdUserAndId_IdOeuvre(Integer idUser, Integer idOeuvre);

}