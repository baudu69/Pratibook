package fr.inextenso.pratibook.service;

import java.util.List;
import java.util.stream.Collectors;

import fr.inextenso.pratibook.dto.AuteurDTO;
import fr.inextenso.pratibook.dto.GenreOeuvreDTO;
import fr.inextenso.pratibook.repository.AuteurRepository;
import org.springframework.stereotype.Service;

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
						.map(GenreOeuvreDTO::fromGenre)
						.collect(Collectors.toSet())))
				.toList();
	}

}
