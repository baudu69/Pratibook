package fr.inextenso.pratibook.service;

import fr.inextenso.pratibook.dto.OeuvreDTO;
import fr.inextenso.pratibook.model.*;
import fr.inextenso.pratibook.repository.OeuvreRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
@Transactional
class ServiceOeuvreTest {

	@MockBean
	private OeuvreRepository oeuvreRepository;

	@Autowired
	private ServiceOeuvre serviceOeuvre;

	@Test
	void findAll() {
		Auteur auteur = new Auteur();
		auteur.setId(1);
		auteur.setNomAuteur("Nom");
		auteur.setPrenomAuteur("Prenom");

		Genre genre = new Genre();
		genre.setId(1);
		genre.setNomGenre("Genre");

		Oeuvre oeuvre = new Oeuvre();
		auteur.setOeuvres(new HashSet<>());
		auteur.getOeuvres().add(oeuvre);
		oeuvre.setId(2);
		oeuvre.setIsbn("1234567890123");
		oeuvre.setTitre("Titre");
		oeuvre.setAnneeSortie((short) 2021);
		oeuvre.setAuteurs(new HashSet<>());
		oeuvre.getAuteurs().add(auteur);
		oeuvre.setGenres(new HashSet<>());
		oeuvre.getGenres().add(genre);

		InstanceOeuvre instanceOeuvre = new InstanceOeuvre();
		instanceOeuvre.setId("1234567890123");
		instanceOeuvre.setOeuvre(oeuvre);
		instanceOeuvre.setEtatDisponibilite(Disponibilite.DISPONIBLE);

		oeuvre.setInstances(new HashSet<>());
		oeuvre.getInstances().add(instanceOeuvre);
		when(oeuvreRepository.findAll()).thenReturn(List.of(oeuvre));

		List<OeuvreDTO> oeuvresDTO = serviceOeuvre.findAll();
		assertEquals(1, oeuvresDTO.size());
		OeuvreDTO oeuvreDTO = oeuvresDTO.get(0);
		assertEquals(oeuvre.getId(), oeuvreDTO.idOeuvre());
		assertEquals(oeuvre.getIsbn(), oeuvreDTO.isbn());
		assertEquals(oeuvre.getTitre(), oeuvreDTO.titre());
		assertEquals(oeuvre.getAnneeSortie(), oeuvreDTO.anneeSortie());
		assertEquals(oeuvre.getAuteurs().size(), oeuvreDTO.auteurs().size());
		assertEquals(oeuvre.getGenres().size(), oeuvreDTO.genres().size());
		assertEquals(oeuvre.getInstances().size(), oeuvreDTO.nbInstanceDisponibles());

		for (Auteur auteur1 : oeuvre.getAuteurs()) {
			assertEquals(auteur1.getNomAuteur(), oeuvreDTO.auteurs().get(0).nomAuteur());
			assertEquals(auteur1.getPrenomAuteur(), oeuvreDTO.auteurs().get(0).prenomAuteur());
		}

		for (Genre genre1 : oeuvre.getGenres()) {
			assertEquals(genre1.getNomGenre(), oeuvreDTO.genres().get(0).nomGenre());
		}
	}

	@Test
	void findById() {
		when(oeuvreRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(NoSuchElementException.class, () -> serviceOeuvre.findById(1));
		Auteur auteur = new Auteur();
		auteur.setId(1);
		auteur.setNomAuteur("Nom");
		auteur.setPrenomAuteur("Prenom");

		Genre genre = new Genre();
		genre.setId(1);
		genre.setNomGenre("Genre");

		Oeuvre oeuvre = new Oeuvre();
		auteur.setOeuvres(new HashSet<>());
		auteur.getOeuvres().add(oeuvre);
		oeuvre.setId(2);
		oeuvre.setIsbn("1234567890123");
		oeuvre.setTitre("Titre");
		oeuvre.setAnneeSortie((short) 2021);
		oeuvre.setAuteurs(new HashSet<>());
		oeuvre.getAuteurs().add(auteur);
		oeuvre.setGenres(new HashSet<>());
		oeuvre.getGenres().add(genre);

		InstanceOeuvre instanceOeuvre = new InstanceOeuvre();
		instanceOeuvre.setId("1234567890123");
		instanceOeuvre.setOeuvre(oeuvre);
		instanceOeuvre.setEtatDisponibilite(Disponibilite.DISPONIBLE);

		oeuvre.setInstances(new HashSet<>());
		oeuvre.getInstances().add(instanceOeuvre);
		when(oeuvreRepository.findById(2)).thenReturn(Optional.of(oeuvre));

		OeuvreDTO oeuvreDTO = serviceOeuvre.findById(2);
		assertEquals(oeuvre.getId(), oeuvreDTO.idOeuvre());
		assertEquals(oeuvre.getIsbn(), oeuvreDTO.isbn());
		assertEquals(oeuvre.getTitre(), oeuvreDTO.titre());
		assertEquals(oeuvre.getAnneeSortie(), oeuvreDTO.anneeSortie());
		assertEquals(oeuvre.getAuteurs().size(), oeuvreDTO.auteurs().size());
		assertEquals(oeuvre.getGenres().size(), oeuvreDTO.genres().size());
		assertEquals(oeuvre.getInstances().size(), oeuvreDTO.nbInstanceDisponibles());

		for (Auteur auteur1 : oeuvre.getAuteurs()) {
			assertEquals(auteur1.getNomAuteur(), oeuvreDTO.auteurs().get(0).nomAuteur());
			assertEquals(auteur1.getPrenomAuteur(), oeuvreDTO.auteurs().get(0).prenomAuteur());
		}

		for (Genre genre1 : oeuvre.getGenres()) {
			assertEquals(genre1.getNomGenre(), oeuvreDTO.genres().get(0).nomGenre());
		}
	}
}