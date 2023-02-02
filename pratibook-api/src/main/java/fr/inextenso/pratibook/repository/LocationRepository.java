package fr.inextenso.pratibook.repository;

import fr.inextenso.pratibook.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Integer> {
	Optional<Location> findByCodeBarreAndIdUtilisateurAndDateRenduReel(String codeBarre, Integer idUtilisateur, LocalDateTime dateRenduReel);

	Optional<Location> findByCodeBarreAndDateRenduReel(String codeBarre, LocalDateTime dateRenduReel);

	List<Location> findByCodeBarreOrderByDateRenduReelDesc(String codeBarre);


}