package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.EmprunterDTO;
import fr.inextenso.pratibook.model.Etat;
import fr.inextenso.pratibook.model.Location;
import fr.inextenso.pratibook.repository.LocationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ServiceLocationTest {
	@MockBean
	private LocationRepository locationRepository;

	@Autowired
	private ServiceLocation serviceLocation;

	@Test
	void emprunter() {
		Location alreadyLocated = new Location();
		alreadyLocated.setDateLocation(LocalDateTime.now());
		when(locationRepository.findByCodeBarreAndIdUtilisateurAndDateRenduReel("codeBarre", 1, null)).thenReturn(Optional.of(alreadyLocated));
		assertThrows(RuntimeException.class, () -> serviceLocation.emprunter(new EmprunterDTO("codeBarre", 1, Etat.ABIME)));
		when(locationRepository.findByCodeBarreAndIdUtilisateurAndDateRenduReel("codeBarre", 2, null)).thenReturn(Optional.empty());
		this.serviceLocation.emprunter(new EmprunterDTO("codeBarre", 2, Etat.ABIME));
		Location alreadyExist = new Location();
		alreadyExist.setId(5);
		alreadyExist.setCodeBarre("codeBarre");
		alreadyExist.setIdUtilisateur(3);
		alreadyExist.setIdEmploye(1);
		alreadyExist.setDateReservation(LocalDateTime.now());
		when(locationRepository.findByCodeBarreAndIdUtilisateurAndDateRenduReel("codeBarre", 3, null)).thenReturn(Optional.of(alreadyExist));
		this.serviceLocation.emprunter(new EmprunterDTO("codeBarre", 3, Etat.ABIME));
	}
}