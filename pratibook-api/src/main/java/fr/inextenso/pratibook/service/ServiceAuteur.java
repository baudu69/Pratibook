package fr.inextenso.pratibook.service;

import java.util.List;
import java.util.stream.Collectors;

import fr.inextenso.pratibook.dto.AuteurDTO;
import fr.inextenso.pratibook.dto.AuteurWithOeuvresDTO;
import fr.inextenso.pratibook.dto.GenreOeuvreDTO;
import fr.inextenso.pratibook.exception.NotFoundException;
import fr.inextenso.pratibook.exception.ReferencedEntityException;
import fr.inextenso.pratibook.model.Auteur;
import fr.inextenso.pratibook.repository.AuteurRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ServiceAuteur {

	private final AuteurRepository auteurRepository;

	public ServiceAuteur(AuteurRepository auteurRepository) {
		this.auteurRepository = auteurRepository;
	}

	public List<AuteurDTO> getAllAuteurs() {
		return this.auteurRepository.findAll()
				.stream()
				.map(auteur -> AuteurDTO.fromAuteur(auteur, auteur.getOeuvres().stream()
						.flatMap(oeuvre -> oeuvre.getGenres().stream())
						.map(GenreOeuvreDTO::new)
						.collect(Collectors.toSet())))
				.toList();
	}

	public AuteurWithOeuvresDTO getAuteurWithOeuvres(Integer id) {
		return this.auteurRepository.findById(id)
				.map(AuteurWithOeuvresDTO::new)
				.orElseThrow(NotFoundException::new);
	}

	public void addAuteur(AuteurDTO auteur) {
		this.auteurRepository.save(createAuteurFromDTO(auteur));
	}

	public void updateAuteur(AuteurDTO dto) {
		final Auteur auteur = this.auteurRepository.findById(dto.idAuteur())
				.orElseThrow(NotFoundException::new);

		auteur.setNomAuteur(dto.nomAuteur());
		auteur.setPrenomAuteur(dto.prenomAuteur());
		auteur.setDateNaissanceAuteur(dto.dateNaissanceAuteur());
		auteur.setDateDecesAuteur(dto.dateDecesAuteur());

		this.auteurRepository.save(auteur);
	}

	public void deleteAuteur(int id) {
		final Auteur auteur = this.auteurRepository.findById(id)
				.orElseThrow(NotFoundException::new);

		if (auteur.getOeuvres().size() > 0) throw new ReferencedEntityException();

		this.auteurRepository.delete(auteur);
	}

	private static Auteur createAuteurFromDTO(AuteurDTO dto) {
		final Auteur auteur = new Auteur();
		auteur.setNomAuteur(dto.nomAuteur());
		auteur.setPrenomAuteur(dto.prenomAuteur());
		auteur.setDateNaissanceAuteur(dto.dateNaissanceAuteur());
		auteur.setDateDecesAuteur(dto.dateDecesAuteur());
		return auteur;
	}

}
