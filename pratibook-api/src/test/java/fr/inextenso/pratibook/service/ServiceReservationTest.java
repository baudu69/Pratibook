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
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

	@MockBean
	private ServiceOeuvre serviceOeuvre;

	@Autowired
	private ServiceReservation serviceReservation;

	@MockBean
	private InstanceOeuvreRepository instanceOeuvreRepository;
	@MockBean
	private LocationRepository locationRepository;

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

	@Test
	void getListeDemandeReservation() {
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setId(1);
		oeuvre.setTitre("test");
		when(oeuvreRepository.findById(1)).thenReturn(Optional.of(oeuvre));
		OeuvreDTO oeuvreDTO = new OeuvreDTO(1, "test", (short) 2021, "123456789", 1, null, null);
		when(serviceOeuvre.findById(1)).thenReturn(oeuvreDTO);
		DemandeReservation demandeReservation = new DemandeReservation();
		demandeReservation.setId(new DemandeReservationId());
		demandeReservation.getId().setIdOeuvre(1);
		demandeReservation.getId().setIdUser(1);
		demandeReservation.setDateDemande(LocalDateTime.now());
		when(demandeReservationRepository.findAll()).thenReturn(List.of(demandeReservation));
		List<DemandeReservationDTO> demandeReservationDTOS = serviceReservation.getListeDemandeReservation();
		assertEquals(1, demandeReservationDTOS.size());
		assertEquals(oeuvreDTO, demandeReservationDTOS.get(0).oeuvreDTO());
		assertEquals(1, demandeReservationDTOS.get(0).idUser());
	}

	@Test
	void validerDemandeReservation() {
		Oeuvre oeuvre = new Oeuvre();
		oeuvre.setId(1);
		oeuvre.setTitre("test");
		InstanceOeuvre instanceOeuvre = new InstanceOeuvre();
		instanceOeuvre.setCodeBarre("codeBarre");
		instanceOeuvre.setEtatDisponibilite(Disponibilite.DISPONIBLE);
		instanceOeuvre.setOeuvre(oeuvre);
		oeuvre.setInstances(new HashSet<>());
		oeuvre.getInstances().add(instanceOeuvre);
		when(oeuvreRepository.findById(1)).thenReturn(Optional.of(oeuvre));
		when(instanceOeuvreRepository.findById("codeBarre")).thenReturn(Optional.of(instanceOeuvre));
		when(demandeReservationRepository.deleteById_IdUserAndId_IdOeuvre(1, 1)).thenReturn(1L);
		when(instanceOeuvreRepository.save(any())).thenReturn(null);
		when(locationRepository.save(any())).thenReturn(null);
		this.serviceReservation.validerDemandeReservation(new ValidationDemandeReservation("codeBarre", 1), 1);
		when(demandeReservationRepository.deleteById_IdUserAndId_IdOeuvre(1, 1)).thenReturn(0L);
		assertThrows(IllegalArgumentException.class, () -> this.serviceReservation.validerDemandeReservation(new ValidationDemandeReservation("codeBarre", 1), 1));
		when(demandeReservationRepository.deleteById_IdUserAndId_IdOeuvre(1, 1)).thenReturn(2L);
		assertThrows(IllegalArgumentException.class, () -> this.serviceReservation.validerDemandeReservation(new ValidationDemandeReservation("codeBarre", 1), 1));
	}
}