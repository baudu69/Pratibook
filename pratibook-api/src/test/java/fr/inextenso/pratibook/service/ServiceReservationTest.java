package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.exception.AlreadyReservedException;
import fr.inextenso.pratibook.exception.NotAvailableException;
import fr.inextenso.pratibook.model.Disponibilite;
import fr.inextenso.pratibook.model.InstanceOeuvre;
import fr.inextenso.pratibook.model.Oeuvre;
import fr.inextenso.pratibook.repository.DemandeReservationRepository;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ServiceReservationTest {

	@MockBean
	private DemandeReservationRepository demandeReservationRepository;
	@MockBean
	private OeuvreRepository oeuvreRepository;

	@Autowired
	private ServiceReservation serviceReservation;

	@Test
	void demandeReservation() {
		when(demandeReservationRepository.existsById_IdUserAndId_IdOeuvre(1, 1)).thenReturn(true);
		assertThrows(AlreadyReservedException.class, () -> serviceReservation.demandeReservation(1, 1));
		final Oeuvre oeuvre = new Oeuvre();
		oeuvre.setInstances(new HashSet<>());
		when(oeuvreRepository.findById(5)).thenReturn(Optional.of(oeuvre));
		assertThrows(NotAvailableException.class, () -> serviceReservation.demandeReservation(5, 1));
		when(oeuvreRepository.findById(6)).thenReturn(Optional.empty());
		assertThrows(IllegalArgumentException.class, () -> serviceReservation.demandeReservation(6, 1));
		when(oeuvreRepository.save(any())).thenReturn(null);
		final InstanceOeuvre instance = new InstanceOeuvre();
		instance.setEtatDisponibilite(Disponibilite.DISPONIBLE);
		oeuvre.getInstances().add(instance);
		serviceReservation.demandeReservation(5, 2);
	}
}